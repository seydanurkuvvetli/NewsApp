package com.example.newsapp.data.remote

import com.example.newsapp.data.model.News
import com.example.newsapp.util.NewsCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country:String="us",
        @Query("category")
        category:String=NewsCategory.BUSINESS.param,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String="b4bd614bf2a447b1adc493b4c946b823"

    ):Response<News>
}