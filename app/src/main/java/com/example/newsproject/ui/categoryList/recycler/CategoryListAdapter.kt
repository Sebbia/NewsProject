package com.example.newsproject.ui.categoryList.recycler;

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.data.Category
import com.example.newsproject.ui.ItemClickListener

class CategoryListAdapter(val listener: ItemClickListener) : RecyclerView.Adapter<CategoryListItemViewHolder>() {
    private var list: List<Category> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_list_item, parent, false)
        return CategoryListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryListItemViewHolder, position: Int) {
        holder.bind(list.get(position), listener)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(categoryList: List<Category>) {
        list = categoryList
        notifyDataSetChanged()
    }
}
