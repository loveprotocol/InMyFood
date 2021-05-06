package com.yes.inmyfood

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.yes.inmyfood.data.Posting
import com.yes.inmyfood.postings.PostingsRvAdapter

@BindingAdapter("app:items")
fun setPostingItems(viewPager2: ViewPager2, items: List<Posting>) {
    (viewPager2.adapter as PostingsRvAdapter).setItems(items)
}