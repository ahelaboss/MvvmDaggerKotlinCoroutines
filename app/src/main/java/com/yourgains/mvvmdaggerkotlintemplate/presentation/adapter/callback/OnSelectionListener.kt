package com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter.callback

interface OnSelectionListener<T> {

    fun onItemSelected(item: T?, isChecked: Boolean)
    fun onItemsSelected(items: List<T>)

}