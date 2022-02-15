package com.example.newsproject.ui.categoryList.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.data.Category
import com.example.newsproject.ui.ItemClickListener

class CategoryListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val nameView: TextView = view.findViewById(R.id.category_name)

    fun bind(category: Category? = null, lister: ItemClickListener) {
        if (category != null) {
            view.setOnClickListener {
                lister.onItemClicked(category.id)
            }
            nameView.text = category.name
        }
    }
}