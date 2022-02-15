package com.example.newsproject.data

interface NewsRepository {
    fun getCategoryList(
        onSuccess: (List<Category>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNewsList(
        categoryId: Long,
        page: Int,
        onSuccess: (List<News>) -> Unit,
        onFailure: (String) -> Unit
    )
    fun getNews(
        newsId: Long,
        onSuccess: (News) -> Unit,
        onPartialSuccess: (News) -> Unit,
        onFailure: (String) -> Unit
    )
}