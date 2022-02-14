package com.example.newsproject.ui.categoryList.recycler;

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class CategoryListDecorator(
    private val spanCount: Int = 1,
    private val spacing: Float = 0F,
    private val includeEdge: Boolean = false
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        val spacingInt = spacing.toInt()
        if (includeEdge) {
            outRect.left = spacingInt - column * spacingInt / spanCount
            outRect.right = (column + 1) * spacingInt / spanCount
            if (position < spanCount) {
                outRect.top = spacingInt
            }
            outRect.bottom = spacingInt
        } else {
            outRect.left = column * spacingInt / spanCount
            outRect.right = spacingInt - (column + 1) * spacingInt / spanCount
            if (position >= spanCount) {
                outRect.top = spacingInt
            }
        }
    }
}
