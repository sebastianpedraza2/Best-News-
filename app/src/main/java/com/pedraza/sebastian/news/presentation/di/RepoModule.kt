package com.pedraza.sebastian.news.presentation.di

import com.pedraza.sebastian.news.data.repo.NewsRepoImpl
import com.pedraza.sebastian.news.data.repo.datasource.NewsRemoteDataSource
import com.pedraza.sebastian.news.domain.repository.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepoModule {

    @Singleton
    @Provides
    fun provideNewsRepo(newsRemoteDataSource: NewsRemoteDataSource): NewsRepo {
        return NewsRepoImpl(newsRemoteDataSource)
    }
}