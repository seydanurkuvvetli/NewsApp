package com.example.newsapp.view.dependencyInjection

import com.example.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapp.view.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {
    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    ):NewsViewModelFactory{
        return NewsViewModelFactory(getNewsHeadlinesUseCase)
    }
}