package com.example.newsproject.data;

import android.util.Log

class NewsRepository {
    private val TAG = "MyNewsRepository"
    private val remoteDS: NewsRemoteDataSource = NewsRemoteDataSource()//TODO temporary stuff
    //private val localDS:

    fun getCategoryList(
        onSuccess: (List<Category>) -> Unit,
        onFailure: () -> Unit
    ) {
        //TODO should ask for data from local DS first and other repo logic
        remoteDS.getCategoryList { result ->
            result
                .onSuccess {
                    Log.d(TAG, "getCategoryList onSuccess called")
                    if (it != null)
                        onSuccess(it.list)
                    else {
                        Log.i(TAG, "categoryList == null error")
                        onFailure()
                    }
                }
                .onFailure {
                    Log.d(TAG, "getCategoryList onFailure called")
                    Log.i(TAG, it.message ?: "Undefine error")
                    onFailure()
                }
        }
    }


    fun getNewsList(
        categoryId: Long,
        page: Int,
        onSuccess: (List<News>) -> Unit,
        onFailure: () -> Unit
    ) {
        remoteDS.getNewsList(categoryId, page) { result ->
            result
                .onSuccess {
                    Log.d(TAG, "getNewsList onSuccess called")
                    if (it != null)
                        onSuccess(it.list)
                    else {
                        Log.i(TAG, "newsList == null error")
                        onFailure()
                    }
                }
                .onFailure {
                    Log.d(TAG, "getNewsList onFailure called")
                    Log.i(TAG, it.message ?: "Undefine error")
                    onFailure()
                }
        }
    }

    fun getNews(
        newsId: Long,
        onSuccess: (News) -> Unit,
        onFailure: () -> Unit
    ) {
        remoteDS.getNews(newsId) { result ->
            result
                .onSuccess {
                    Log.d(TAG, "getNews onSuccess called")
                    if (it != null)
                        onSuccess(it.news)
                    else {
                        Log.i(TAG, "News == null error")
                        onFailure()
                    }
                }
                .onFailure {
                    Log.d(TAG, "getNews onFailure called")
                    Log.i(TAG, it.message ?: "Undefine error")
                    onFailure()
                }
        }
    }
}
