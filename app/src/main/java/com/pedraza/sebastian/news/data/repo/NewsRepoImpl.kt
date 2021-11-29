package com.pedraza.sebastian.news.data.repo

import com.pedraza.sebastian.news.data.model.ApiResponse
import com.pedraza.sebastian.news.data.model.Article
import com.pedraza.sebastian.news.data.repo.datasource.NewsRemoteDataSource
import com.pedraza.sebastian.news.domain.repository.NewsRepo
import com.pedraza.sebastian.news.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepoImpl(private val newsRemoteDataSource: NewsRemoteDataSource) : NewsRepo {

    override suspend fun getNewsHeadlines(
        country: String,
        page: Int
    ): Resource<ApiResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    private fun responseToResource(response: Response<ApiResponse>): Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedHeadlines(querySearch: String): Resource<ApiResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}