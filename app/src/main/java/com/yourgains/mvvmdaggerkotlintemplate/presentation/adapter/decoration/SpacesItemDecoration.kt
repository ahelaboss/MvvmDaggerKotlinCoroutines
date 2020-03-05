package com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpacesItemDecoration() : RecyclerView.ItemDecoration() {

    constructor(space: Int, isHorizontal: Boolean = false) : this() {
        this.bottom = space
        this.right = space
        this.left = space
        this.top = space
        this.isHorizontal = isHorizontal
    }

    constructor(
        left: Int,
        right: Int,
        bottom: Int,
        top: Int,
        isHorizontal: Boolean = false
    ) : this() {
        this.bottom = bottom
        this.right = right
        this.left = left
        this.top = top
        this.isHorizontal = isHorizontal
    }

    var left: Int = 0
    var right: Int = 0
    private var bottom: Int = 0
    private var top: Int = 0
    private var isHorizontal: Boolean = false

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = left
        outRect.right = right
        outRect.bottom = bottom
        outRect.top = top

        // Add top margin only for the first selectedItem to avoid double space between items
        if (isHorizontal.not() && parent.getChildAdapterPosition(view) == 0) {
            outRect.top = top
        }
    }
}