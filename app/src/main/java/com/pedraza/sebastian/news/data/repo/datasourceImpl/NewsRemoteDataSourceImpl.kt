package com.pedraza.sebastian.news.data.repo.datasourceImpl

import com.pedraza.sebastian.news.data.api.NewsApiService
import com.pedraza.sebastian.news.data.repo.datasource.NewsRemoteDataSource
import com.pedraza.sebastian.news.data.model.ApiResponse
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService,
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(
        country: String,
        page: Int
    ): Response<ApiResponse> =
        newsApiService.getTopHeadlines(country = country, page = page)
}