package com.example.newsapp.util

enum class NewsCategory (val param: String){//kategorilerimiz belli başlı sayıda olduğu için ve kendine ait bir id si olduğu için enum class yazmamız uygun
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")

}