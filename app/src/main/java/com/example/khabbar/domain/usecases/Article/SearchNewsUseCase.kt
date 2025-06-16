package com.example.khabbar.domain.usecases.Article

import com.example.khabbar.domain.model.Article
import com.example.khabbar.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(query: String, sources: List<String>): Result<List<Article>> {
        return if (query.isBlank()) {
            Result.failure(Exception("Search query cannot be empty"))
        } else {
            repository.searchNews(query, sources)
        }
    }
}