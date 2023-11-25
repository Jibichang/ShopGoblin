package com.wa.shopgoblin.util

import android.content.Context

fun loadImage(context: Context, mImageName: String): Int {
    return context.resources.getIdentifier(mImageName, "drawable", context.packageName)
}