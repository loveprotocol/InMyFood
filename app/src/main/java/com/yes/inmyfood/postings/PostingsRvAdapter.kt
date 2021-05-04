package com.yes.inmyfood.postings

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yes.inmyfood.data.Posting
import com.yes.inmyfood.databinding.FragmentPostingsItemBinding

class PostingsRvAdapter() : RecyclerView.Adapter<PostingsRvAdapter.ViewHolder>() {

    private var values: List<Posting> = listOf()
    private lateinit var viewBinding: FragmentPostingsItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = FragmentPostingsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.fragment_posting_list_item, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.viewBinding.fragPostingsItemIvFood.setImageDrawable(item.foodDrawable)
        holder.viewBinding.fragPostingsItemTvTag.text = item.tagList.toString()
    }

    override fun getItemCount(): Int = values.size

    fun setItems(items: List<Posting>) {
        values = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val viewBinding: FragmentPostingsItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {}
}