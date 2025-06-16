package com.example.khabbar.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.khabbar.data.remote.dto.NewsResponce
import com.example.khabbar.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
//suspend  fun getNews() : NewsResponce

    fun getSavedArticles(): Flow<List<Article>>           //room db
    suspend fun saveArticle(article: Article)
    suspend fun isArticleSaved(url: String): Boolean
    suspend fun searchNews(query: String, sources: List<String>): Result<List<Article>>

    suspend fun deleteArticle(url: String)

}