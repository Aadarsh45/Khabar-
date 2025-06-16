package com.example.khabbar.presentation.detail

import android.content.Intent
import android.net.Uri
import com.example.khabbar.domain.model.Article
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.khabbar.presentation.Dimens.MediumPadding1
import com.example.khabbar.R
import com.example.khabbar.presentation.bookmark.ArticleEvent
import com.example.khabbar.presentation.bookmark.ArticleViewModel
import com.example.khabbar.presentation.detail.component.DetailsTopBar

@Composable
fun DetailsScreen(
    article: Article,
    event: @Composable (ArticleEvent) -> Unit,
    navigateUp: () -> Unit,
    viewModel: ArticleViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(context = context)
                .data(article.urlToImage)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.8f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            // Top Bar
            DetailsTopBar(
                onBrowsingClick = {
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(article.url)
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, article.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onBookMarkClick = {

                    viewModel.onEvent(ArticleEvent.SaveArticle(article))
                },
                onBackClick = navigateUp
            )

            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 32.dp,
                            topEnd = 32.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .background(
                        Color.White.copy(alpha = 0.95f)
                    )
                    .padding(top = 24.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        start = MediumPadding1,
                        end = MediumPadding1,
                        bottom = MediumPadding1
                    )
                ) {
                    item {
                        Text(
                            text = article.title,
                            style = MaterialTheme.typography.displaySmall,
                            color = colorResource(id = R.color.text_title),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = article.content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorResource(id = R.color.body),
                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.4
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                Intent(Intent.ACTION_VIEW).also {
                                    it.data = Uri.parse(article.url)
                                    if (it.resolveActivity(context.packageManager) != null) {
                                        context.startActivity(it)
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Read Full Article",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

