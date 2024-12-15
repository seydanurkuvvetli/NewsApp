package com.example.newsapp.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.viewpager2.widget.ViewPager2
import com.example.newsapp.R
import com.example.newsapp.data.model.News
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.util.NewsCategory
import com.example.newsapp.util.Resource
import com.example.newsapp.view.adapter.NewsAdapter
import com.example.newsapp.view.viewmodel.NewsViewModel
import com.example.newsapp.view.viewmodel.NewsViewModelFactory
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(NewsViewModel::class.java)
    }

    @Inject
    lateinit var factory: NewsViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private var page = 1
    private var totalResults = 0
    private var newsCategory: String = NewsCategory.BUSINESS.param

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            //Category configuration
            setcategoryCard()
            isCategorySelected(true, layoutCategory.cardBusiness, layoutCategory.textViewBusiness)

            viewModel.getNewsHeadlines(
                context = requireContext(),
                country = "us",
                category = newsCategory,
                page = page
            )
            viewModel.headlinesData.observe(viewLifecycleOwner, ::setData)
            viewPager.apply {
                adapter = newsAdapter
                registerOnPageChangeCallback(scrollListener)
            }
        }
    }

    @OptIn(UnstableApi::class)
    private fun setData(resource: Resource<News>?) {
        when (resource) {
            is Resource.Success -> {
                resource.data?.let {
                    newsAdapter.differ.submitList(it.articles?.toList())
                    totalResults = it.totalResults
                    showLoadingAnimation()
                    Log.i(TAG, it.status)
                }
            }

            is Resource.Error -> {
                resource.message?.let { error ->
                    showLoadingAnimation()
                    viewModel.getNewsHeadlines(
                        context = requireContext(),
                        country = "us",
                        category = newsCategory,
                        page = page
                    )
                    Log.e(TAG, error)

                }
            }

            is Resource.Loading -> {
                showLoadingAnimation(true)
            }

            else -> Log.d(TAG, "Resource not found!")

        }
    }

    private val scrollListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (position == newsAdapter.itemCount - 1) {
                page += 1
                if (page == (totalResults / 20) + 2) page =
                    1 // If max page ((totalResults / 20) + 1) + 1 make return first
                updateNewsHeadlines()
            }
        }
    }

    private fun FragmentNewsBinding.setcategoryCard() {
        layoutCategory.apply {
            setCategoryInfo(cardBusiness, textViewBusiness, NewsCategory.BUSINESS.param)
            setCategoryInfo(
                cardEntertainment,
                textViewEntertainment,
                NewsCategory.ENTERTAINMENT.param
            )
            setCategoryInfo(cardGeneral, textViewGeneral, NewsCategory.GENERAL.param)
            setCategoryInfo(cardHealth, textViewHealth, NewsCategory.HEALTH.param)
            setCategoryInfo(cardScience, textViewScience, NewsCategory.SCIENCE.param)
            setCategoryInfo(cardSports, textViewSports, NewsCategory.SPORTS.param)
            setCategoryInfo(cardTechnology, textViewTechnology, NewsCategory.TECHNOLOGY.param)
        }
    }

    private fun setCategoryInfo(
        card: MaterialCardView,
        textView: AppCompatTextView,
        categoryParam: String
    ) {
        card.setOnClickListener {
            if (newsCategory != categoryParam) {
                setUnSelectCategoryCard()
                page = 1
                newsCategory = categoryParam
                updateNewsHeadlines()
                isCategorySelected(true, card, textView)
            }
        }
    }

    private fun updateNewsHeadlines() {
        showLoadingAnimation(true)
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getNewsHeadlines(
                context = requireContext(),
                country = "us",
                category = newsCategory,
                page = page
            )
        }, 1000)
        binding.viewPager.currentItem = 0

    }

    private fun setUnSelectCategoryCard() {
        with(binding.layoutCategory) {
            isCategorySelected(false, cardBusiness, textViewBusiness)
            isCategorySelected(false, cardEntertainment, textViewEntertainment)
            isCategorySelected(false, cardGeneral, textViewGeneral)
            isCategorySelected(false, cardHealth, textViewHealth)
            isCategorySelected(false, cardScience, textViewScience)
            isCategorySelected(false, cardSports, textViewSports)
            isCategorySelected(false, cardTechnology, textViewTechnology)
        }
    }

    private fun isCategorySelected(
        selected: Boolean,
        card: MaterialCardView,
        textView: AppCompatTextView
    ) {
        if (selected) {
            card.setCardBackgroundColor(requireContext().getColor(R.color.black))
            textView.setTextColor(requireContext().getColor(R.color.white))
        } else {
            card.setCardBackgroundColor(requireContext().getColor(R.color.white))
            textView.setTextColor(requireContext().getColor(R.color.black))
        }

    }

    private fun showLoadingAnimation(isShown: Boolean = false) {
        with(binding) {
            lottieAnimation.visibility = if (isShown) View.VISIBLE else View.INVISIBLE
            viewPager.visibility = if (isShown) View.INVISIBLE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = NewsFragment::class.simpleName.toString()
    }
}

