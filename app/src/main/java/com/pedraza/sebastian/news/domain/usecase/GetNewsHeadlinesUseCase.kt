package com.pedraza.sebastian.news.domain.usecase

import com.pedraza.sebastian.news.domain.repository.NewsRepo

class GetNewsHeadlinesUseCase(private val newsRepo: NewsRepo) {
    suspend fun execute(
        country: String,
        page: Int
    ) = newsRepo.getNewsHeadlines(country, page)

}