package com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter

import android.util.SparseBooleanArray
import androidx.recyclerview.widget.RecyclerView
import com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter.callback.OnCheckBoxSelectionListener
import com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter.callback.OnSelectionListener
import com.yourgains.mvvmdaggerkotlintemplate.presentation.adapter.viewholder.BaseSelectableViewHolder


abstract class BaseSelectionAdapter<In, Vh : BaseSelectableViewHolder<In>>(
    var list: MutableList<In>? = arrayListOf(),
    var onSelectionListener: OnSelectionListener<In>? = null
) : RecyclerView.Adapter<Vh>(), OnCheckBoxSelectionListener {

    private val selectedItems = SparseBooleanArray()
    private var isSingleMode = true

    var isInChoiceMode: Boolean = true
        set(value) {
            field = value
            if (!value) {
                selectedItems.clear()
                notifyItemRangeChanged(0, list!!.size, false)
            }
        }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.bind(it, selectedItems.get(position), {}) }
    }

    override fun selectionClick(position: Int, checked: Boolean) {
        if (isSingleMode) {
            val idsSelectedItems = getIdsSelectedItems().toMutableList()
            idsSelectedItems.remove(position)
            clearSelectedState(idsSelectedItems)
            if (checked) {
                selectedItems.put(position, checked)
            }
            notifyItemChanged(position, checked)
            updateSelectionListener(position, checked)
        } else {
            selectedItems.delete(position)
            if (checked) selectedItems.put(position, true)
            val isInChoiceMode = selectedItems.size() != 0
            if (this.isInChoiceMode != isInChoiceMode) {
                this.isInChoiceMode = isInChoiceMode
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun updateSelectionListener(position: Int, checked: Boolean) {
        onSelectionListener?.let {
            it.onItemSelected(list?.get(position), checked)
            it.onItemsSelected(getSelectedItems())
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    fun getItems(): List<In>? = list

    open fun setItems(list: List<In>?) {
        this.list = list?.toMutableList()
        notifyDataSetChanged()
    }

    fun updateItems(list: List<In>?) {
        list?.let { this.list?.addAll(it) }
        notifyDataSetChanged()
    }

    open fun getItem(position: Int): In? = list?.getOrNull(position)

    fun getSelectedItems(): List<In> {
        val items = arrayListOf<In>()
        for (i in 0 until selectedItems.size()) {
            val data = list?.get(selectedItems.keyAt(i))
            data?.let { items.add(it) }
        }
        return items
    }

    fun getIdsSelectedItems(): List<Int> {
        val items = arrayListOf(selectedItems.size())
        for (i in 0 until selectedItems.size()) {
            items.add(selectedItems.keyAt(i))
        }
        return items
    }

    fun clearSelectedState(selection: List<Int>) {
        selectedItems.clear()
        for (position in selection) {
            notifyItemChanged(position, false)
        }
    }

    fun clearSelectedStateAll() {
        val selection = getIdsSelectedItems()
        selectedItems.clear()
        for (position in selection) {
            notifyItemChanged(position, false)
            updateSelectionListener(position, false)
        }
    }
}