package com.example.newsapp.view.dependencyInjection

import com.example.newsapp.data.remote.APIService
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.data.repository.datasourceImpl.NewRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: APIService
    ): NewsRemoteDataSource {
        return NewRemoteDataSourceImpl(newsAPIService)
    }
}