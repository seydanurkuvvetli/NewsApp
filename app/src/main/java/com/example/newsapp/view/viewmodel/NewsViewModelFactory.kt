package com.example.newsapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(getNewsHeadlinesUseCase) as T
    }
}