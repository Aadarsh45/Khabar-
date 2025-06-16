package com.example.khabbar.domain.usecases.Article

import com.example.khabbar.domain.model.Article
import com.example.khabbar.domain.repository.NewsRepository
import javax.inject.Inject

class SaveArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        repository.saveArticle(article)
    }
}