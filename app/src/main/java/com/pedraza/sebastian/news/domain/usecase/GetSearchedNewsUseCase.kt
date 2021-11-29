package com.pedraza.sebastian.news.domain.usecase

import com.pedraza.sebastian.news.domain.repository.NewsRepo

class GetSearchedNewsUseCase(private val newsRepo: NewsRepo) {
    suspend fun execute(searchQuery: String) = newsRepo.getSearchedHeadlines(searchQuery)

}