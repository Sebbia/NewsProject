package com.example.newsproject.ui.news

import androidx.lifecycle.MutableLiveData
import com.example.newsproject.data.News

interface NewsViewModel {
    val news: MutableLiveData<News>
}