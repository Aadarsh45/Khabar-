package com.example.khabbar.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.khabbar.domain.model.Article
import com.example.khabbar.presentation.bookmark.ArticleViewModel
import com.example.khabbar.presentation.bookmark.BookmarkScreen
import com.example.khabbar.presentation.detail.DetailsScreen
import com.example.khabbar.presentation.home.HomeScreen
import com.example.khabbar.presentation.home.HomeViewModel
import com.example.khabbar.presentation.onboarding.OnBoardingScreen
import com.example.khabbar.presentation.onboarding.OnBoardingViewModel
import com.example.khabbar.presentation.search.SearchScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    onEvent = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigate = { article ->
                        // Convert article to JSON for navigation
                        val articleJson = Gson().toJson(article)
                        navController.navigate(
                            Route.DetailsScreen.route.replace(
                                "{article}",
                                URLEncoder.encode(articleJson, "utf-8")
                            )
                        )
                    },
                    toNavigateBookmark = {
                        navController.navigate(Route.BookmarkScreen.route)
                    },
                    {
                        navController.navigate(Route.SearchScreen.route)
                    }
                )
            }



            composable(route = Route.BookmarkScreen.route) {
                val viewModel: ArticleViewModel = hiltViewModel()
                BookmarkScreen(
                    navigateUp = { navController.popBackStack() },
                    onNavigate = { article ->
                        // Convert article to JSON for navigation
                        val articleJson = Gson().toJson(article)
                        navController.navigate(
                            Route.DetailsScreen.route.replace(
                                "{article}",
                                URLEncoder.encode(articleJson, "utf-8")
                            )
                        )}

                )

            }


            }

            composable(route = Route.DetailsScreen.route) { backStackEntry ->
                val articleJson = URLDecoder.decode(
                    backStackEntry.arguments?.getString("article") ?: "",
                    "utf-8"
                )
                val article = Gson().fromJson(articleJson, Article::class.java)

                DetailsScreen(
                    article = article,
                    event = {
                        val viewModel: ArticleViewModel = hiltViewModel()
                        BookmarkScreen(
                            navigateUp = { navController.popBackStack() },
                            onNavigate = { article ->
                                // Convert article to JSON for navigation
                                val articleJson = Gson().toJson(article)
                                navController.navigate(
                                    Route.DetailsScreen.route.replace(
                                        "{article}",
                                        URLEncoder.encode(articleJson, "utf-8")
                                    )
                                )}

                        )

                        // Handle other events if needed

                    },
                    navigateUp = { navController.popBackStack() }
                )

            }

            composable(route = Route.SearchScreen.route) {
                SearchScreen(
                    onNavigate = { article ->
                        // Convert article to JSON for navigation
                        val articleJson = Gson().toJson(article)
                        navController.navigate(
                            Route.DetailsScreen.route.replace(
                                "{article}",
                                URLEncoder.encode(articleJson, "utf-8")
                            )
                        )
                    })
            }}

        }





