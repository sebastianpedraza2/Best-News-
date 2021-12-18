package com.pedraza.sebastian.news.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object StringTestModule {


    @Provides
    @Singleton
    @Named("String1")
    fun provideTestString(): String {
        return "This is a new string"
    }

    @Provides
    @Singleton
    @Named("String2")
    fun provideTestString2(): String {
        return "This is a new string2"
    }

}