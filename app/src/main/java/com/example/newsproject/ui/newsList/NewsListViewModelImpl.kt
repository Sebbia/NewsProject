package com.example.newsproject.ui.newsList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.newsproject.utils.SingleLiveEvent
import com.example.newsproject.data.News
import com.example.newsproject.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class NewsListViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(),
    NewsListViewModel {
    private val TAG = "MyNewsListViewModel"
    override val list: MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    override val navEvent: SingleLiveEvent<NavDirections> = SingleLiveEvent()

    init {
        Log.d(TAG, "was initialized")
    }

    override fun getNewsList(newsId: Long, page: Int) {
        repository.getNewsList(
            newsId,
            page,
            onSuccess = {
                Log.d(TAG, "getNewsList onSuccess called")
                list.value = it
            },
            onFailure = {
                Log.d(TAG, "getNewsList onFailure called")
                //TODO not yet impl
            }
        )
    }

    override fun onItemClicked(newsId: Long) {
        Log.d(TAG, "onItemClicked called")
        navEvent.value = NewsListFragmentDirections
            .actionNewsListFragmentToNewsFragment(newsId)
    }
}