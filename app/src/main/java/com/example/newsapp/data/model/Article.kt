package com.example.newsapp.data.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Article(
    val articleUUID: String = UUID.randomUUID().toString(),
    @SerializedName("author")
    val articleAuthor: String?,
    @SerializedName("content")
    val articleCntent: String?,
    @SerializedName("description")
    val articleDescription: String?,
    @SerializedName("publishedAt")
    val ArticlePublishedAt: String?,
    @SerializedName("source")
    val ArticleSource: Source?,
    @SerializedName("title")
    val articleTitle: String?,
    @SerializedName("url")
    val articleUrl: String?,
    @SerializedName("urlToImage")
    val articleUrlToImage: String?
)