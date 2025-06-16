package com.example.khabbar.data.remote.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.khabbar.data.remote.local.Entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao{
    @Insert
    suspend fun insertArticle(article: ArticleEntity)

    @Query("SELECT * FROM articles WHERE isSaved = 1")
    fun getSavedArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT EXISTS(SELECT * FROM articles WHERE url = :url AND isSaved = 1)")
    suspend fun isArticleSaved(url: String): Boolean

    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun deleteArticle(url: String)

}