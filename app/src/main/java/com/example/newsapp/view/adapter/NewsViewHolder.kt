package com.example.newsapp.view.adapter

import android.graphics.Color
import android.view.View
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.model.Article
import com.example.newsapp.databinding.ItemNewsBinding

class NewsViewHolder(
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val circularProgressDrawable = CircularProgressDrawable(binding.root.context)

    fun bind(article: Article) {

        circularProgressDrawable.run {
            strokeWidth = 5f
            centerRadius = 50f
            setColorSchemeColors(Color.WHITE)
            this.start()
        }
        binding.apply {
            webView.visibility = View.GONE
            article.articleUrl?.let { url ->
                card.setOnClickListener {
                    webView.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = WebViewClient()
                    webView.loadUrl(url)
                }
            }
            textTitle.text = article.articleTitle
            textDate.text = article.ArticlePublishedAt!!.split("T")[0]
            textDescription.text = article.articleDescription ?: " "
            setImage(article, imageViewBackground)
            setImage(article, imageViewThumbnail)

        }
    }

    private fun setImage(article: Article, imageView: ImageView) {
        Glide.with(
            binding.root.context
        )
            .load(if (!article.articleUrlToImage.isNullOrEmpty()) article.articleUrlToImage else R.drawable.news_background)
            .placeholder(circularProgressDrawable)
            .into(imageView)
    }
}