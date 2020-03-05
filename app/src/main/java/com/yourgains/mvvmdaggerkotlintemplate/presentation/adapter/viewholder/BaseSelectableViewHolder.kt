package com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Checkable
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter.callback.OnItemClickListener

abstract class BaseSelectableViewHolder<T>(
    parent: ViewGroup,
    @LayoutRes layoutId: Int,
    val listener: OnItemClickListener<T>? = null
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    abstract fun bind(data: T, isSelected: Boolean, action: () -> Unit)

    abstract fun getCheckableItem(): Checkable
}