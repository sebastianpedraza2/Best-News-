package com.pedraza.sebastian.news.data.repo.datasource

import com.pedraza.sebastian.news.data.model.ApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String,
                                page: Int): Response<ApiResponse>
}