package com.example.khabbar.data.remote.dto

import com.example.khabbar.domain.model.Article

data class NewsResponce(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)