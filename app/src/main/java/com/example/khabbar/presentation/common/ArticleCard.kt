package com.example.khabbar.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.copy
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.khabbar.R
import com.example.khabbar.domain.model.Article
import com.example.khabbar.domain.model.Source
import com.example.khabbar.presentation.Dimens.ArticleCardSize
import com.example.khabbar.presentation.Dimens.ExtraSmallPadding
import com.example.khabbar.presentation.Dimens.ExtraSmallPadding2
import com.example.khabbar.presentation.Dimens.smallIconSize

@Composable
fun ArticleCard(
    modifier : Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit

) {
    Row(

        modifier = modifier.fillMaxWidth()
        .clickable{
            onClick (article)
        }

    ){
        AsyncImage(                                                //coil library
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context = LocalContext.current).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(4.dp))

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Text(
                "${article.title}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp,fontStyle = FontStyle.Italic),
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )



            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(color = colorResource(id = R.color.body)),
                    overflow = TextOverflow.Ellipsis

                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(smallIconSize),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(color = colorResource(id = R.color.body)),


                    )
            }
        }




    }


}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    ArticleCard(modifier = Modifier.padding(16.dp),
        article = Article(
            author = "John Doe",
    content = "Original content.",
    description = "Original desc.",
    publishedAt = "2024-01-01T00:00:00Z",
    source = Source(id = "news-x", name = "News X"),
    title = "Original Title",
    url = "https://ichef.bbci.co.uk/news/1024/cpsprodpb/088a/live/431e72d0-42b8-11f0-8adf-5d3d31c48348.jpg.webp",
    urlToImage = "https://ichef.bbci.co.uk/news/1024/cpsprodpb/088a/live/431e72d0-42b8-11f0-8adf-5d3d31c48348.jpg.webp.jpg"
    ),
        onClick = {})
}

