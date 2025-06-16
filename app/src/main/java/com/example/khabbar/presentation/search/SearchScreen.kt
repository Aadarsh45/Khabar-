package com.example.khabbar.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khabbar.domain.model.Article
import com.example.khabbar.presentation.common.ArticleCard
import com.example.khabbar.presentation.common.EmptyScreen
import com.example.khabbar.presentation.home.component.SearchBar
import com.example.khabbar.presentation.search.component.ArticleItem

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigate: (Article) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = viewModel.query,
            readOnly = false,
            onValueChange = viewModel::updateQuery,
            onSearch = { viewModel.searchNews() },
            modifier = Modifier,
            onClick = { }
        )

        Spacer(modifier = Modifier.height(8.dp))


        when {
            viewModel.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            viewModel.error != null -> {Log.d("SearchScreen", "Error")
            }

            viewModel.articles.isEmpty() && viewModel.query.isNotBlank() -> {
                EmptyScreen(

                )
            }

            else -> {
                LazyColumn {
                    items(viewModel.articles.size) { article ->
                        ArticleCard(article = viewModel.articles[article]){
                            onNavigate(viewModel.articles[article])
                        }

                    }
                }
            }
        }
    }
}





