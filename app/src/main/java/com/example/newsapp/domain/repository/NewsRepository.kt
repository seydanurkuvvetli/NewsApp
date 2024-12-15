package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.News
import com.example.newsapp.util.Resource

interface  NewsRepository {
     suspend fun getNewsHeadlines(country:String,category:String,page:Int):Resource<News>
}