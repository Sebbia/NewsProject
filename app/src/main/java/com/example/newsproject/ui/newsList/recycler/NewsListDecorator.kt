package com.example.newsproject.ui.newsList.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/*
    Pass parameters without screen density multiplier
 */
class NewsListDecorator constructor(
    private val marginHorizontal: Int = 0,
    private val marginVertical: Int = 0,
    private val marginBetweenItems: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view);
        outRect.right = marginHorizontal
        outRect.left = marginHorizontal
        if (position == state.itemCount-1) // if item is last
            outRect.bottom = marginVertical
        if (position == 0)
            outRect.top = marginVertical
        else
            outRect.top = marginBetweenItems
    }
}