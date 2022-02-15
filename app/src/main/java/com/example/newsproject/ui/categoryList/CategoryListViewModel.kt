package com.example.newsproject.ui.categoryList

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.example.newsproject.data.Category
import com.example.newsproject.utils.SingleLiveEvent

interface CategoryListViewModel {
    val list: MutableLiveData<List<Category>>
    val navEvent: SingleLiveEvent<NavDirections>
    fun onItemClicked(categoryId: Long)
}