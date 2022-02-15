package com.example.newsproject.ui.newsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is categoryList Fragment"
    }
    val text: LiveData<String> = _text
}