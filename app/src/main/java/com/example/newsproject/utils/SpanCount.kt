package com.example.newsproject.utils;

import android.content.Context

/**
    Pass parameters with screen density multiplier.
    resources.getDimension() will return Float = (dp value * screen density)
    just pass it as itemWidth
 */
object SpanCount {
    fun getSpanCount(context: Context, itemWidth: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val itemTrueWidth = itemWidth / displayMetrics.density
        return (dpWidth / itemTrueWidth).toInt()
    }
}
