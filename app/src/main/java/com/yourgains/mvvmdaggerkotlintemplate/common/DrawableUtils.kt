package com.yourgains.mvvmdaggerkotlintemplate.common

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

object DrawableUtils {

    fun getBitmap(drawable: Drawable, width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}