package com.example.khabbar.data.remote

import androidx.compose.ui.unit.Constraints
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.khabbar.domain.model.Article
import com.example.khabbar.presentation.onboarding.pages
import com.example.khabbar.utils.Constants

class NewsPagingSources(
    private val newsApi: NewsApi,
    private val sources: String
): PagingSource<Int, Article>() {

    var totalResult = 0;

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponce = newsApi.getNews(sources = sources, page = page)
            totalResult += newsResponce.totalResults
            val article = newsResponce.articles.distinctBy { it.title }
            LoadResult.Page(
                data = article,
                nextKey = if (totalResult == newsResponce.totalResults) null else page + 1,
                prevKey = null
            )

        }catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}