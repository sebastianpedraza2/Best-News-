package com.pedraza.sebastian.news.domain.usecase

import com.pedraza.sebastian.news.domain.repository.NewsRepo

class GetSavedNewsUseCase(private val newsRepo: NewsRepo) {
    fun execute() = newsRepo.getSavedNews()

}