package com.example.newsproject.data

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRemoteDataSource {
    private val BASE_URL = "http://testtask.sebbia.com/v1/news/"
    private val TAG = "MyNewsRemoteDataSource"

    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(RetrofitService::class.java)

    fun getCategoryList(callback: (Result<ApiCategoryList?>) -> Unit) {
        Log.d(TAG, "retrofitService.getCategoryList called")
        val call = retrofitService.getCategoryList()
        call.enqueue(object : Callback<ApiCategoryList?> {
            override fun onResponse(
                call: Call<ApiCategoryList?>,
                response: Response<ApiCategoryList?>
            ) {
                Log.d(TAG, "getCategoryList onResponse called")
                callback(
                    Result.success(
                        response.body()
                    )
                )
            }

            override fun onFailure(call: Call<ApiCategoryList?>, throwable: Throwable) {
                Log.d(TAG, "getCategoryList onFailure called")
                callback(
                    Result.failure(
                        throwable
                    )
                )
            }
        }
        )
    }


    fun getNewsList(
        categoryId: Long,
        page: Int,
        callback: (Result<ApiNewsList?>) -> Unit
    ) {
        Log.d(TAG, "retrofitService.getNewsList called with categoryId = $categoryId, page = $page")
        val call = retrofitService.getNewsList(categoryId, page)
        call.enqueue(object : Callback<ApiNewsList?> {
            override fun onResponse(
                call: Call<ApiNewsList?>,
                response: Response<ApiNewsList?>
            ) {
                Log.d(TAG, "getNewsList onResponse called ${response.body()}")
                callback(
                    Result.success(
                        response.body()
                    )
                )
            }

            override fun onFailure(call: Call<ApiNewsList?>, throwable: Throwable) {
                Log.d(TAG, "getNewsList onFailure called")
                callback(
                    Result.failure(
                        throwable
                    )
                )
            }
        }
        )
    }

    fun getNews(
        newsId: Long,
        callback: (Result<ApiNews?>) -> Unit
    ) {
        Log.d(TAG, "retrofitService.getNews called with newsId = $newsId")
        val call = retrofitService.getNews(newsId)
        call.enqueue(object : Callback<ApiNews?> {
            override fun onResponse(
                call: Call<ApiNews?>,
                response: Response<ApiNews?>
            ) {
                Log.d(TAG, "getNews onResponse called")
                callback(
                    Result.success(
                        response.body()
                    )
                )
            }

            override fun onFailure(call: Call<ApiNews?>, throwable: Throwable) {
                Log.d(TAG, "getNews onFailure called")
                callback(
                    Result.failure(
                        throwable
                    )
                )
            }
        }
        )
    }

}