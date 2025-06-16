package com.example.khabbar.domain.usecases.Article

import com.example.khabbar.domain.model.Article
import com.example.khabbar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedArticleUseCases @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getSavedArticles()
    }
}