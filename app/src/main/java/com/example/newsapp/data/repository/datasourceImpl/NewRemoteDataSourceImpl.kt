package com.example.newsapp.data.repository.datasourceImpl

import com.example.newsapp.data.model.News
import com.example.newsapp.data.remote.APIService
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class NewRemoteDataSourceImpl @Inject constructor(
private val apiService: APIService
):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(
        country: String,
        category: String,
        page: Int
    ): Response<News> {
       return apiService.getTopHeadlines(country,category,page)
    }
}