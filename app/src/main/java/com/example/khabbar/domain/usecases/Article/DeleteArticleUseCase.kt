package com.example.khabbar.domain.usecases.Article

import com.example.khabbar.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String) {
        newsRepository.deleteArticle(url)
    }
}