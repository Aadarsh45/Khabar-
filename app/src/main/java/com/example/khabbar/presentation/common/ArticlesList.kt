package com.example.khabbar.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.khabbar.domain.model.Article


@Composable
fun ArticlesList(
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    // Handle loading/error states
    when {
        articles.isLoading() -> LoadingShimmer()
        articles.hasError() -> EmptyScreen(error = articles.getError())
        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(articles.itemCount) { index ->
                    articles[index]?.let { article ->
                        ArticleCard(
                            article = article,
                            onClick = { onClick(article) }
                        )
                    }
                }
            }
        }
    }
}

// Helper extensions for cleaner state checks
private fun LazyPagingItems<*>.isLoading() =
    loadState.refresh is LoadState.Loading

private fun LazyPagingItems<*>.hasError() =
    loadState.refresh is LoadState.Error

private fun LazyPagingItems<*>.getError() =
    (loadState.refresh as? LoadState.Error)

