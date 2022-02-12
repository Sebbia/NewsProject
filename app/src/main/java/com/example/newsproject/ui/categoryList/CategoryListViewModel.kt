package com.example.newsproject.ui.categoryList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.myapp.SingleLiveEvent
import com.example.newsproject.data.Category
import com.example.newsproject.data.NewsRepository
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {
    private val TAG = "MyCategoryListViewModel"
    val list: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()
    val navEvent: SingleLiveEvent<NavDirections> = SingleLiveEvent<NavDirections>()

    private val repository = NewsRepository() // TODO temporary

    init {
        //asking repo for data on ViewModel init mb is not the best idea
        //but I quite like it
        viewModelScope.launch {
            repository.getCategoryList(
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
    }

    fun onItemClicked(categoryId: Long){
        Log.d(TAG, "onItemClicked called")
        navEvent.value = CategoryListFragmentDirections
            .actionCategoryListFragmentToNewsListFragment(categoryId)
    }
}