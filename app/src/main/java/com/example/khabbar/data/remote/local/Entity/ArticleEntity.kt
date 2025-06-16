package com.example.khabbar.data.remote.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.khabbar.domain.model.Article
import com.example.khabbar.domain.model.Source

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val url: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val sourceId: String?,
    val sourceName: String,
    val title: String,
    val urlToImage: String?,
    val isSaved: Boolean = true
)

