package com.yes.inmyfood.postings

import android.content.Context
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import com.yes.inmyfood.R
import com.yes.inmyfood.data.Posting
import com.yes.inmyfood.databinding.FragmentPostingsItemBinding
import com.yes.inmyfood.util.HashTag
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
            tags += "#$it "
        }

        val hashTagSpans: ArrayList<IntArray> = getSpans(tags, '#')

        val tagsContent = SpannableString(tags)

        hashTagSpans.forEach { span ->
            val hashTagStart = span[0]
            val hashTagEnd = span[1]

            val hashTag = HashTag(context)
            hashTag.setOnClickEventListener(object: HashTag.ClickEventListener {
                override fun onClickEvent(data: String) {
                    Toast.makeText(context, "tag #$data clicked", Toast.LENGTH_SHORT).show()
                }})

            tagsContent.setSpan(hashTag, hashTagStart, hashTagEnd, 0)
        }



        with(holder.viewBinding.fragPostingsItemTvTag) {
            movementMethod = LinkMovementMethod.getInstance()
            text = tagsContent
        }
    }

    private fun getSpans(tags: String, prefix: Char): ArrayList<IntArray> {
        val spans = ArrayList<IntArray>()

        val pattern = Pattern.compile("$prefix\\w+");
        val matcher = pattern.matcher(tags)

      //  Log.i("matcher", "matcher start:${matcher.regionStart()}, end:${matcher.regionEnd()}")

        // check all occurrences
        while (matcher.find()) {
            val currentSpan = IntArray(2)
            currentSpan[0] = matcher.start()
            currentSpan[1] = matcher.end()
            spans.add(currentSpan)
        }

        return spans
    }

    override fun getItemCount(): Int = values.size

    fun setItems(items: List<Posting>) {
        values = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val viewBinding: FragmentPostingsItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {}
}