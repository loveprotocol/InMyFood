package com.yes.inmyfood

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginRight
import com.yes.inmyfood.databinding.FragmentPostingListItemBinding

class PostingListViewAdapter() : RecyclerView.Adapter<PostingListViewAdapter.ViewHolder>() {

    private var values: List<Posting> = listOf()
    private lateinit var viewBinding: FragmentPostingListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = FragmentPostingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.fragment_posting_list_item, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.viewBinding.fragPostingListItemTvDate.text = JodaTimeHelper.convertTimeStampToDateString(item.uploadDateTime, "yyyy-MM-dd")
        holder.viewBinding.fragPostingListItemIvFood.setImageDrawable(item.foodDrawable)
        holder.viewBinding.fragPostingListItemTvTag.text = item.tagList.toString()
    }

    override fun getItemCount(): Int = values.size

    fun setItems(items: List<Posting>) {
        values = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val viewBinding: FragmentPostingListItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {}
}