package com.example.khabbar.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.khabbar.R

data class Page(
    val tittle : String,
    val desc : String,
    @DrawableRes val img : Int,
)


val pages = listOf(
    Page(
        tittle = "Stay Updated, Instantly",
        desc = "Get breaking news alerts and personalized updates delivered straight to your device. Never miss a moment.",
        img = R.drawable.onboarding1 // e.g., an image representing notifications or speed
    ),
    Page(
        tittle = "Explore Diverse Categories",
        desc = "From world events and politics to technology, sports, and entertainment – find stories that matter to you.",
        img = R.drawable.onboarding2 // e.g., an image showing various category icons
    ),
    Page(
        tittle = "Your News, Your Way",
        desc = "Customize your news feed, follow your favorite topics and sources, and save articles for later reading.",
        img = R.drawable.onboarding3 // e.g., an image representing personalization or bookmarks
    )
)