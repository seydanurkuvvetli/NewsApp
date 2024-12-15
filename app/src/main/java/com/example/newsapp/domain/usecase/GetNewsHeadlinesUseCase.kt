package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.News
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Resource
import javax.inject.Inject

class GetNewsHeadlinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(country:String,category:String,page:Int):Resource<News>{
        return newsRepository.getNewsHeadlines(country,category,page)
    }
}