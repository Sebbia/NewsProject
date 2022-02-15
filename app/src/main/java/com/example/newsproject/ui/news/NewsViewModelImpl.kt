package com.example.newsproject.ui.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.newsproject.data.News
import com.example.newsproject.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(
    private val repository: NewsRepository,
    private val state: SavedStateHandle
) : ViewModel(),
    NewsViewModel {
    private val TAG = "MyNewsViewModel"
    override val news: MutableLiveData<News> = MutableLiveData()
    val newsId = state.get<Long>("newsId") ?: -1

    init {
        Log.d(TAG, "was initialized")
        repository.getNews(
            newsId,
            onSuccess = {
                Log.d(TAG, "getNews onSuccess called")
                news.value = it
            },
            onPartialSuccess = {
                Log.d(TAG, "getNews onPartialSuccess called")
                news.value = it
            },
            onFailure = {
                Log.d(TAG, "getNews onFailure called")
                //TODO not yet impl
            }
        )
    }
}