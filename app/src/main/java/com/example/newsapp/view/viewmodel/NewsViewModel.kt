package com.example.newsapp.view.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.News
import com.example.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapp.util.NetworkController
import com.example.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel

class NewsViewModel @Inject constructor(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
):ViewModel(){
    private val headlines:MutableLiveData<Resource<News>> = MutableLiveData()//mutablelivedata yazılabilir okunabilir
    val headlinesData:LiveData<Resource<News>>//livedata sadece okunabilir
        get()=headlines
    fun getNewsHeadlines(
        context: Context?=null,
        country:String,
        category:String,
        page:Int
    )=viewModelScope.launch(Dispatchers.IO){
        headlines.postValue(Resource.Loading())
        try{
            if(NetworkController.isNetworkAvailable(context)){
                val apiResult = getNewsHeadlinesUseCase.execute(country, category, page)
                headlines.postValue(apiResult)
            }else{
                headlines.postValue(Resource.Error("Internet is not available!"))
            }
        }catch (e:Exception){
            headlines.postValue(Resource.Error(e.message.toString()))
        }
    }
}