package com.yes.inmyfood

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yes.inmyfood.data.Posting
import com.yes.inmyfood.postings.PostingsRvAdapter

@BindingAdapter("app:items")
fun setPostingItems(recyclerView: RecyclerView, items: List<Posting>) {
    (recyclerView.adapter as PostingsRvAdapter).setItems(items)
    recyclerView.scrollToPosition(items.lastIndex)
    recyclerView.smoothScrollBy(10, 0)  // centerZoom 조절을 위해 임의로 좌표 이동
}