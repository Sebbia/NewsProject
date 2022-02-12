package com.example.newsproject.ui.newsList.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.data.News
import com.example.newsproject.ui.ItemClickListener

class NewsListItemViewHolder (private val view: View) : RecyclerView.ViewHolder(view) {
    private val titleView: TextView = view.findViewById(R.id.category_name)
//    private val dateView: TextView = view.findViewById(R.id)
//    private val shortDesView: TextView = view.findViewById(R.id)

    fun bind(news: News? = null, lister: ItemClickListener) {
        if (news != null) {
            view.setOnClickListener {
                lister.onItemClicked(news.id)
            }
            titleView.text = news.title
            //dateView.text = news.date // + formatter
            //shortDesView.text = news.shortDescription
        }
    }
}