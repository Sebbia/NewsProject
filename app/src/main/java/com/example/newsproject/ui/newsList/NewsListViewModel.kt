package com.example.newsproject.ui.newsList

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.example.newsproject.data.News
import com.example.newsproject.utils.SingleLiveEvent

interface NewsListViewModel {
    val list: MutableLiveData<MutableList<News>>
    val navEvent: SingleLiveEvent<NavDirections>
    fun onCreateView()
    fun getNewPage()
    fun onDestroyView()
    fun onItemClicked(newsId: Long)
}