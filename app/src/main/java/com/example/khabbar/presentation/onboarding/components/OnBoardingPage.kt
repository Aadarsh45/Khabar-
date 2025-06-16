package com.example.khabbar.presentation.onboarding.components

import android.content.res.Configuration
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.khabbar.R
import com.example.khabbar.presentation.Dimens.MediumPadding1
import com.example.khabbar.presentation.Dimens.MediumPadding2
import com.example.khabbar.presentation.onboarding.Page

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f),
            painter = painterResource(page.img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = page.tittle,
            modifier = modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.text_medium),


        )

        Text(
            text = page.desc,
            modifier = modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.text_medium)

            )
        )

    }

}


@Preview(showBackground = true, heightDp = 700, widthDp = 300)
@Preview(showBackground = true, heightDp = 700, widthDp = 300, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingPagePreview() {
    // Call the OnBoardingPage composable with sample data
    OnBoardingPage(
        // modifier = Modifier.fillMaxSize(), // Optionally provide a modifier for the preview
        page = Page( // Create a sample Page object
            tittle = "Welcome to Khabbar!",
            desc = "Discover the latest news and updates from around the world, tailored just for you.",
            img = R.drawable.onboarding1 // Replace with an actual drawable resource from your project
        )
    )
}




