package com.example.newsapp.data.repository.datasource

import com.example.newsapp.data.model.News
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country:String,category:String,page:Int) : Response<News>//burda eğer room ile çalışsaydık news yerine article kullanacaktık
    
}