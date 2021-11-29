package com.pedraza.sebastian.news.presentation.di

import com.pedraza.sebastian.news.domain.repository.NewsRepo
import com.pedraza.sebastian.news.domain.usecase.GetNewsHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadlinesUseCase(newsRepo: NewsRepo): GetNewsHeadlinesUseCase {
        return GetNewsHeadlinesUseCase(newsRepo)
    }
}