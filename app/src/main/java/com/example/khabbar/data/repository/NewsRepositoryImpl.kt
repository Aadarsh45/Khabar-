package com.example.khabbar.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.khabbar.data.remote.NewsApi
import com.example.khabbar.data.remote.NewsPagingSources
import com.example.khabbar.data.remote.dto.NewsResponce
import com.example.khabbar.data.remote.local.Entity.ArticleEntity
import com.example.khabbar.data.remote.local.dao.ArticleDao
import com.example.khabbar.domain.model.Article
import com.example.khabbar.domain.model.Source
import com.example.khabbar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val articleDao: ArticleDao

) : NewsRepository {



    private val pageSize =  10
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        val pager = Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                NewsPagingSources(
                    newsApi = newsApi,
                    sources = sources.joinToString(",")
                )
            }
        ).flow
        return pager
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getSavedArticles().map { articles ->
            articles.map { it.toArticle() }
        }
    }

    override suspend fun saveArticle(article: Article) {
        articleDao.insertArticle(article.toEntity())
    }

    override suspend fun isArticleSaved(url: String): Boolean {
        return articleDao.isArticleSaved(url)
    }
    override suspend fun searchNews(query: String, sources: List<String>): Result<List<Article>> {
        return try {
            val response = newsApi.searchNews(
                searchQuery = query,
                sources = sources.joinToString(",")
            )

            val articles = response.articles
                .distinctBy { it.title } // Remove duplicates


            Result.success(articles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteArticle(url: String) {
        articleDao.deleteArticle(url)
    }

}



private fun Article.toEntity() = ArticleEntity(
    url = url,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceId = source.id,
    sourceName = source.name,
    title = title,
    urlToImage = urlToImage,
    isSaved = true
)

private fun ArticleEntity.toArticle() = Article(
    url = url,
    author = author.toString(),
    content = content.toString(),
    description = description.toString(),
    publishedAt = publishedAt,
    source = Source(sourceId.toString(),sourceName),
    title = title,
    urlToImage = urlToImage.toString(),
)

