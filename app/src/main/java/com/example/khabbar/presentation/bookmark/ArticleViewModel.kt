package com.example.khabbar.presentation.bookmark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabbar.domain.model.Article
import com.example.khabbar.domain.usecases.Article.DeleteArticleUseCase
import com.example.khabbar.domain.usecases.Article.GetSavedArticleUseCases
import com.example.khabbar.domain.usecases.Article.IsArticleSavedUseCase
import com.example.khabbar.domain.usecases.Article.SaveArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val saveArticleUseCase: SaveArticleUseCase,
    private val getSavedArticlesUseCase: GetSavedArticleUseCases,
    private val isArticleSavedUseCase: IsArticleSavedUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase
) : ViewModel() {

    private var _newsState = mutableStateOf(ArticleState())
    val newsState = _newsState

    // Track saved status for UI
    var isBookmarked by mutableStateOf(false)
        private set

    init {
        loadSavedArticles()
    }

    fun onEvent(event: ArticleEvent) {
        when(event) {
            is ArticleEvent.SaveArticle -> toggleSaveArticle(event.article)
        }
    }

    private fun toggleSaveArticle(article: Article) {
        viewModelScope.launch {
            saveArticleUseCase(article)
            isBookmarked = true
            loadSavedArticles()
        }
    }

    private fun toNavigateDetails() {
        viewModelScope.launch {


        }
    }
    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            deleteArticleUseCase(article.url)
            loadSavedArticles() // Refresh the list after deletion
        }
    }

    private suspend fun checkSavedStatus(url: String) {
        isBookmarked = isArticleSavedUseCase(url)
    }

    private fun loadSavedArticles() {
        viewModelScope.launch {
            getSavedArticlesUseCase().collect { savedArticles ->
                _newsState.value = _newsState.value.copy(savedArticles = savedArticles)
            }
        }
    }
}

sealed class ArticleEvent {
    data class SaveArticle(val article: Article) : ArticleEvent()

}

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val savedArticles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)