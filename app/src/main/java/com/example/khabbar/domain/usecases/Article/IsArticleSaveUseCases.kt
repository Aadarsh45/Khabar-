package com.example.khabbar.domain.usecases.Article

import com.example.khabbar.domain.repository.NewsRepository
import javax.inject.Inject

class IsArticleSavedUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(url: String): Boolean {
        return repository.isArticleSaved(url)
    }
}
