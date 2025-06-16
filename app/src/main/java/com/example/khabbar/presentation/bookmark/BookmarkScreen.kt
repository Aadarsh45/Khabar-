package com.example.khabbar.presentation.bookmark

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khabbar.R
import com.example.khabbar.domain.model.Article
import com.example.khabbar.presentation.Dimens.smallIconSize
import com.example.khabbar.presentation.bookmark.component.BookmarkTopBar
import com.example.khabbar.presentation.common.ArticleCard
import com.example.khabbar.presentation.detail.component.DetailsTopBar
import com.example.khabbar.presentation.navgraph.Route
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
fun BookmarkScreen(
    viewModel: ArticleViewModel = hiltViewModel(),
    navigateUp: () -> Boolean,
    onNavigate: (Article) -> Unit
) {
    val state = viewModel.newsState.value
    val count = state.savedArticles.size

    BookmarkTopBar {
        navigateUp()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(smallIconSize)
            .padding(top = 96.dp)
    ) {
        items(state.savedArticles.size) { article -> // Add a key
            SwipeToDeleteContainer(
                item = article,
                onDelete = { viewModel.deleteArticle(state.savedArticles[article]) }
            ) {
                ArticleCard(
                    article = state.savedArticles[article],
                    onClick = {
                        onNavigate(
                            state.savedArticles[article],

                            )
                    })


//                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    content: @Composable (T) -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                onDelete(item)
            }
            true
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.Transparent
                    else -> Color.Transparent
                }
            )
            val alignment = Alignment.CenterEnd
            val icon = Icons.Default.Delete

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Delete",
                    tint = Color.White
                )
            }
        },
        dismissContent = { content(item) }
    )
}

