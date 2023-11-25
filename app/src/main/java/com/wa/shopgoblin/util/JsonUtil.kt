package com.wa.shopgoblin.util

import android.content.Context
import com.google.gson.Gson
import okio.buffer
import okio.source
import java.nio.charset.Charset

fun readJsonFromAssets(context: Context, filePath: String): String? {
    try {
        val source = context.assets.open(filePath).source().buffer()
        return source.readByteString().string(Charset.forName("utf-8"))

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

fun <A> A.toJson(): String {
    return Gson().toJson(this) ?: ""
}