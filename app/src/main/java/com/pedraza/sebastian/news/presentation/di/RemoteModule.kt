package com.pedraza.sebastian.news.presentation.di

import com.pedraza.sebastian.news.data.api.NewsApiService
import com.pedraza.sebastian.news.data.repo.datasource.NewsRemoteDataSource
import com.pedraza.sebastian.news.data.repo.datasourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RemoteModule {

    /**
     * Newsapiservice se obtiene en el netmodule
     */
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApiService)
    }
}