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

//Cached Data
data class NewsCache(
    val newsPageList: MutableList<NewsPage> = mutableListOf(),
    var categoryList: List<Category> = listOf()
) {

    fun containsNewsPage(categoryId: Long, page: Int): Boolean {
        for (item in newsPageList)
            if (item.categoryId == categoryId && item.page == page) return true
        return false
    }

    fun addNewsPage(categoryId: Long, page: Int, list: List<News>) {
        newsPageList.add(NewsPage(categoryId, page, list))
    }

    fun getNewsList(categoryId: Long, page: Int): List<News> {
        for (item in newsPageList)
            if (item.categoryId == categoryId && item.page == page) return item.newsList
        return listOf()
    }

    fun getNews(newsId: Long): News {
        for (page in newsPageList)
            for (news in page.newsList)
                if (news.id == newsId) return news
        return News()
    }
}

data class NewsPage(
    val categoryId: Long = -1,
    val page: Int = -1,
    val newsList: List<News> = listOf()
)


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