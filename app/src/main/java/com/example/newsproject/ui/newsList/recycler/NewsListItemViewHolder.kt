package com.example.newsproject.ui.newsList.recycler

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.data.News
import com.example.newsproject.ui.ItemClickListener
import java.text.SimpleDateFormat

class NewsListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val titleView: TextView = view.findViewById(R.id.news_title)
    private val dateView: TextView = view.findViewById(R.id.news_date)
    private val shortDesView: TextView = view.findViewById(R.id.news_shortDescription)

    @SuppressLint("SimpleDateFormat")
    fun bind(news: News? = null, lister: ItemClickListener) {
        if (news != null) {
            view.setOnClickListener {
                lister.onItemClicked(news.id)
            }
            titleView.text = news.title
            dateView.text = SimpleDateFormat("dd.MM.yyyy HH:mm").format(news.date)
            shortDesView.text = news.shortDescription
        }
    }
}