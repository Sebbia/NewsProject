package com.example.newsproject.ui.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsproject.data.News
import com.example.newsproject.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(),
    NewsViewModel {
    private val TAG = "MyNewsViewModel"
    override val news: MutableLiveData<News> = MutableLiveData<News>()

    override fun getNews(newsId: Long) {
        repository.getNews(
            newsId,
            onSuccess = {
                Log.d(TAG, "getCategoryList onSuccess called")
                news.value = it
            },
            onPartialSuccess = {
                Log.d(TAG, "getCategoryList onPartialSuccess called")
                news.value = it
            },
            onFailure = {
                Log.d(TAG, "getCategoryList onFailure called")
                //TODO not yet impl
            }
        )
    }
}