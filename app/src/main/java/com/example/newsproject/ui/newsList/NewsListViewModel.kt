package com.example.newsproject.ui.newsList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.myapp.SingleLiveEvent
import com.example.newsproject.data.Category
import com.example.newsproject.data.News
import com.example.newsproject.data.NewsRepository
import com.example.newsproject.ui.categoryList.CategoryListFragmentDirections
import kotlinx.coroutines.launch

class NewsListViewModel : ViewModel() {
    private val TAG = "MyNewsListViewModel"
    val list: MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    val navEvent: SingleLiveEvent<NavDirections> = SingleLiveEvent<NavDirections>()

    private val repository = NewsRepository() // TODO temporary

    fun getNewsList(newsId: Long, page: Int){
        repository.getNewsList(
            newsId,
            page,
            onSuccess = {
                Log.d(TAG, "getCategoryList onSuccess called")
                list.value = it
            },
            onFailure = {
                Log.d(TAG, "getCategoryList onFailure called")
                //TODO not yet impl
            }
        )
    }
    fun onItemClicked(newsId: Long){
        Log.d(TAG, "onItemClicked called")
        navEvent.value = NewsListFragmentDirections
            .actionNewsListFragmentToNewsFragment(newsId)
    }

}