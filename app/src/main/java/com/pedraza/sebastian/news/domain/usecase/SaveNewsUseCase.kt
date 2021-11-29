package com.pedraza.sebastian.news.domain.usecase

import com.pedraza.sebastian.news.data.model.Article
import com.pedraza.sebastian.news.domain.repository.NewsRepo

class SaveNewsUseCase(private val newsRepo: NewsRepo) {
    suspend fun execute(article: Article) = newsRepo.saveArticle(article)

}