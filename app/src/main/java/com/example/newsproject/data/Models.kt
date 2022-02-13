package com.example.newsproject.data

import java.time.Instant
import java.util.Date

//API Models
data class ApiCategoryList(
    val code: Int = -1,
    val message: String = "",
    val list: List<Category> = emptyList()
)

data class ApiNewsList(
    val code: Int = -1,
    val message: String = "",
    val list: List<News> = emptyList()
)

data class ApiNews(
    val code: Int = -1,
    val message: String = "",
    val news: News = News()
)

//Lists' Extensions
fun List<News>.getNews(id: Long): News {
    for (item in this)
        if (item.id == id) return item
    return News()
}

//Data Model
data class Category(
    val id: Long = -1,
    val name: String = ""
)

data class News(
    val id: Long = -1,
    val title: String = "",
    val date: Date = Date.from(Instant.now()),
    val shortDescription: String = "",
    val fullDescription: String = "",
    var state: Boolean = false
)