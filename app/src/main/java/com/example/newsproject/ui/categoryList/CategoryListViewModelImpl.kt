package com.example.newsproject.ui.categoryList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.newsproject.utils.SingleLiveEvent
import com.example.newsproject.data.Category
import com.example.newsproject.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) :
    ViewModel(),
    CategoryListViewModel {
    private val TAG = "MyCategoryListViewModel"
    override val list: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()
    override val navEvent: SingleLiveEvent<NavDirections> = SingleLiveEvent()

    init {
        Log.d(TAG, "was initialized")
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

    override fun onItemClicked(categoryId: Long) {
        Log.d(TAG, "onItemClicked called")
        navEvent.value = CategoryListFragmentDirections
            .actionCategoryListFragmentToNewsListFragment(categoryId)
    }
}