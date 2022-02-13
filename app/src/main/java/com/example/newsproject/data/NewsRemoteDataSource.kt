package com.example.newsproject.data

interface NewsRemoteDataSource {
    fun getCategoryList(callback: (Result<ApiCategoryList?>) -> Unit)

    fun getNewsList(
        categoryId: Long,
        page: Int,
        callback: (Result<ApiNewsList?>) -> Unit
    )

    fun getNews(
        newsId: Long,
        callback: (Result<ApiNews?>) -> Unit
    )
}