package com.example.newsproject.ui.newsList.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.data.News
import com.example.newsproject.ui.ItemClickListener

class NewsListAdapter(val listener: ItemClickListener) :
    RecyclerView.Adapter<NewsListItemViewHolder>() {
    private var list: List<News> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.news_list_item, parent, false)
        return NewsListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsListItemViewHolder, position: Int) {
        holder.bind(list.get(position), listener)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newsList: List<News>) {
        list = newsList
        notifyDataSetChanged()
    }
}