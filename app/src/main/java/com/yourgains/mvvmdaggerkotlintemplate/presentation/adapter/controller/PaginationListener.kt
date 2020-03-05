package com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter.controller

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager?

        layoutManager?.let {
            val visible = it.childCount
            val total = it.itemCount
            val firstVisible = it.findFirstVisibleItemPosition()

            if (!isLoading() && !isLastPage() && firstVisible > 0 && (visible + firstVisible) >= total)
                loadMoreItems()

        }
    }

    abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}