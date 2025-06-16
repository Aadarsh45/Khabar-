package com.example.khabbar.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabbar.domain.model.Article
import com.example.khabbar.domain.usecases.Article.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    var query by mutableStateOf("")
        private set

    var articles by mutableStateOf<List<Article>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun updateQuery(newQuery: String) {
        query = newQuery
        error = null
    }

    fun searchNews() {
        viewModelScope.launch {
            isLoading = true
            error = null

            searchNewsUseCase(
                query = query,
                sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
            ).fold(
                onSuccess = {
                    articles = it
                    isLoading = false
                },
                onFailure = {
                    error = it.message ?: "Unknown error occurred"
                    articles = emptyList()
                    isLoading = false
                }
            )
        }
    }
}