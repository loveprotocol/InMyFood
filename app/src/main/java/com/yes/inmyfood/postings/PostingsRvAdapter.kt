package com.yes.inmyfood.postings

import android.content.Context
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yes.inmyfood.R
import com.yes.inmyfood.data.Posting
import com.yes.inmyfood.databinding.FragmentPostingsItemBinding
import com.yes.inmyfood.util.CustomMovementMethod
import java.util.regex.Pattern

class PostingsRvAdapter() : RecyclerView.Adapter<PostingsRvAdapter.ViewHolder>() {

    private var values: List<Posting> = listOf()
    private lateinit var viewBinding: FragmentPostingsItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = FragmentPostingsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context

//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.fragment_posting_list_item, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.viewBinding.fragPostingsItemIvFood.setImageDrawable(item.foodDrawable)

        var tags = ""
        item.tagList.forEach {
            tags += "#$it  "
        }

        val tagTv = holder.viewBinding.fragPostingsItemTvTag
        with(tagTv) {
            text = tags
            highlightColor = context.getColor(android.R.color.transparent)
            setLinkTextColor(context.getColor(R.color.linkColor))

            val pattern = Pattern.compile("#\\w+")
            val transformFilter = Linkify.TransformFilter { _, url ->
                return@TransformFilter "https://www.google.com/search?q=${url.substring(1)}"
            }

            Linkify.addLinks(this, pattern, null, null, transformFilter)
            movementMethod = CustomMovementMethod.getInstance()
        }
    }

    override fun getItemCount(): Int = values.size

    fun setItems(items: List<Posting>) {
        values = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val viewBinding: FragmentPostingsItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {}
}