package com.yourgains.mvvmdaggerkotlintemplate.common

import android.content.Context
import android.os.Parcel
import android.view.View
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.yourgains.mvvmdaggerkotlintemplate.common.event.StateEvent

fun Fragment.getDimens(@DimenRes id: Int): Int = resources.getDimensionPixelSize(id)

fun Context.getDimens(@DimenRes id: Int): Int = resources.getDimensionPixelSize(id)

fun View.getDimens(@DimenRes id: Int): Int = resources.getDimensionPixelSize(id)

fun Parcel.readNullableString() = readString() ?: ""

fun Parcel.createStringArrayListOrEmptyList() = createStringArrayList() ?: emptyList<String>()

fun Parcel.readBoolean() = readByte() != 0.toByte()

fun AppCompatEditText.placeCursorToEnd() {
    this.post {
        this.text?.let { this.setSelection(it.length) }
    }
}

fun View.showEmptyView(state: StateEvent) {
    visibility = when (state) {
        StateEvent.HIDE -> View.GONE
        StateEvent.SHOW -> View.VISIBLE
    }
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

//fun AppCompatImageView.loadWithGlide(
//    url: String?,
//    @DrawableRes placeHolder: Int = 0,
//    isCenterCrop: Boolean = true
//) {
//    url?.let {
//        val glideUrl = GlideUtil.getGlideUrl(it)
//        val glide = GlideApp.with(this)
//            .load(glideUrl)
//            .placeholder(placeHolder)
//        takeIf { isCenterCrop }?.let { glide.centerCrop() }
//        glide.into(this)
//    } ?: setImageResource(placeHolder)
//}