package com.example.newsproject.data;

import android.util.Log
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDS: NewsRemoteDataSource
) : NewsRepository {
    private val TAG = "MyNewsRepository"

    //by Google definition it's in-memory cache -_-
    private var categoryList: List<Category> = listOf()
    private var newsList: List<News> = listOf()

    override fun getCategoryList(
        onSuccess: (List<Category>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        if (categoryList.isNotEmpty()) {
            onSuccess(categoryList)
        } else {
            remoteDS.getCategoryList { result ->
                result
                    .onSuccess {
                        Log.d(TAG, "getCategoryList onSuccess called")
                        if (it != null) {
                            if (it.code == 0) {
                                categoryList = it.list
                                onSuccess(it.list)
                            } else {
                                Log.i(TAG, it.message)
                                onFailure(it.message)
                            }
                        } else {
                            Log.i(TAG, "categoryList == null error")
                            onFailure("Undefined error")
                        }
                    }
                    .onFailure {
                        Log.d(TAG, "getCategoryList onFailure called")
                        Log.i(TAG, it.message ?: "Undefine error")
                        onFailure(it.message ?: "Undefine error")
                    }
            }
        }
    }

    override fun getNewsList(
        categoryId: Long,
        page: Int,
        onSuccess: (List<News>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        if (newsList.isNotEmpty()) {
            onSuccess(newsList)
        } else {
            remoteDS.getNewsList(categoryId, page) { result ->
                result
                    .onSuccess {
                        Log.d(TAG, "getNewsList onSuccess called")
                        if (it != null) {
                            if (it.code == 0) {
                                newsList = it.list
                                onSuccess(it.list)
                            } else {
                                Log.i(TAG, it.message)
                                onFailure(it.message)
                            }
                        } else {
                            Log.i(TAG, "newsList == null error")
                            onFailure("Undefined error")
                        }
                    }
                    .onFailure {
                        Log.d(TAG, "getNewsList onFailure called")
                        Log.i(TAG, it.message ?: "Undefine error")
                        onFailure(it.message ?: "Undefine error")
                    }
            }
        }
    }

    override fun getNews(
        newsId: Long,
        onSuccess: (News) -> Unit,
        onPartialSuccess: (News) -> Unit,
        //here Failure is error during fullDescription loading
        onFailure: (String) -> Unit
    ) {
        val currentNews = newsList.getNews(newsId)
        //State, where currentNews == null, is unreachable with current navigation logic
        if (currentNews.state) {//TODO check logic
            onSuccess(currentNews)
        } else {
            onPartialSuccess(currentNews)
            getNewsFromRemote(newsId, onSuccess, onFailure)
        }
    }

    private fun getNewsFromRemote(
        newsId: Long,
        onSuccess: (News) -> Unit,
        onFailure: (String) -> Unit
    ) {
        remoteDS.getNews(newsId) { result ->
            result
                .onSuccess {
                    Log.d(TAG, "getNews onSuccess called")
                    if (it != null) { //we got something from api
                        if (it.code == 0) {
                            newsList.getNews(newsId).state = true
                            onSuccess(it.news)
                        } else {
                            Log.i(TAG, it.message)
                            onFailure(it.message)
                        }
                    } else { //we got null object from api
                        Log.i(TAG, "news == null error")
                        onFailure("Undefined error")
                    }
                }
                .onFailure {
                    Log.d(TAG, "getNews onFailure called")
                    Log.i(TAG, it.message ?: "Undefine error")
                    onFailure(it.message ?: "Undefine error")
                }
        }
    }
}
