package com.yes.inmyfood

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:items")
fun setPostingItems(recyclerView: RecyclerView, items: List<Posting>) {
    (recyclerView.adapter as PostingListViewAdapter).setItems(items)
}