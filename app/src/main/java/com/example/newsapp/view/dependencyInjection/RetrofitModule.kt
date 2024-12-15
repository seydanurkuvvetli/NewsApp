package com.example.newsapp.view.dependencyInjection

import com.example.newsapp.data.remote.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Singleton
    @Provides
    fun providerRetrofit(
        okHttpClient: OkHttpClient
    ):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://newsapi.org")
            .build()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor

    ):OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30L,TimeUnit.SECONDS)
            .writeTimeout(30L,TimeUnit.SECONDS)
            .build()
    }
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor():HttpLoggingInterceptor{
        return  HttpLoggingInterceptor().also {
            it.level=HttpLoggingInterceptor.Level.BODY
        }
    }
    @Singleton
    @Provides
    fun providesNewsAPIService(retrofit: Retrofit):APIService{
        return retrofit.create(APIService::class.java)
    }
}