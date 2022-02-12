package com.example.newsproject.data

import java.time.Instant
import java.util.Date

data class ApiCategoryList(
    val code: Int = -1,
    val list: List<Category> = emptyList()
)

data class ApiNewsList(
    val code: Int = -1,
    val list: List<News> = emptyList()
)

data class ApiNews(
    val id: Int = -1,
    val news: News = News()
)

data class Category(
    val id: Long = -1,
    val name: String = ""
)

data class News(
    val id: Long = -1,
    val title: String = "",
    val date: Date = Date.from(Instant.now()),
    val shortDescription: String = ""
    //val fullDescription: String = ""
)