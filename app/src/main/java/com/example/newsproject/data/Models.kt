package com.example.newsproject.data

import java.util.*

data class CategoryList(
    val list: List<Category>
) {
}
data class Category(
    val id: Int,
    val name: String,
    val list: List<News>
) {
}
data class News(
    val id: Int,
    val title: String,
    val date: Date,
    val shortDescription: String,
    val fullDescription: String
)