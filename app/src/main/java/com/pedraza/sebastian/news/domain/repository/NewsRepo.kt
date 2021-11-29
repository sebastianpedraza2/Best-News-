package com.pedraza.sebastian.news.domain.repository

import com.pedraza.sebastian.news.data.model.ApiResponse
import com.pedraza.sebastian.news.data.model.Article
import com.pedraza.sebastian.news.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    suspend fun getNewsHeadlines(
        country: String,
        page: Int
    ): Resource<ApiResponse>

    suspend fun getSearchedHeadlines(querySearch: String): Resource<ApiResponse>
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun getSavedNews(): Flow<List<Article>> // Flow no necesario suspend

}