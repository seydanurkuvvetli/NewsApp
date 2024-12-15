package com.example.newsapp.data.repository.datasourceImpl

import com.example.newsapp.data.model.News
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Resource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource:NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(
        country: String,
        category: String,
        page: Int
    ): Resource<News> {
        val apiResult=newsRemoteDataSource.getTopHeadlines(country,category,page)
        if (apiResult.isSuccessful){
            apiResult.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(apiResult.message())
    }
}