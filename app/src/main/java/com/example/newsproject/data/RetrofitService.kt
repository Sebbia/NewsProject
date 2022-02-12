package com.example.newsproject.data

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("categories")
    fun getCategoryList(): Call<ApiCategoryList>

    @GET("news/categories/{id}/news")//TODO check query building
    fun getNewsList(
        @Path("id") id: Long,
        @Query("page") page: Int
    ): Call<ApiNewsList>

    @GET("news/details")
    fun getNews(@Query("id") id: Long): Call<ApiNews>
}