package com.yourgains.mvvmdaggerkotlintemplate.common

import android.content.Context
import android.net.ConnectivityManager
import com.yourgains.mvvmdaggerkotlintemplate.App
import java.net.URLEncoder

object NetworkUtils {

    const val UTF_8 = "utf-8"

    fun isOnline(): Boolean {
        val context = App.appComponent.context()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm?.activeNetworkInfo?.isConnected == true
    }

    fun getSearchQuery(query: String): String = URLEncoder.encode("*$query*", UTF_8)
}