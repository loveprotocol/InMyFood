package com.yes.inmyfood

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions

class FeedRecyclerAdapter(private val dataSet: Array<String>): RecyclerView.Adapter<FeedRecyclerAdapter.FeedViewHolder>() {
    lateinit var parentContext: Context

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postImageView : AppCompatImageView =itemView.findViewById(R.id.item_main_feed_post_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        parentContext = parent.context
        return FeedViewHolder(LayoutInflater.from(parentContext).inflate(R.layout.item_main_feed, parent, false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val rm : RequestManager = Glide.with(parentContext)

        val rb : RequestBuilder<Drawable> = when (position % 12) {
            0 -> rm.load(R.drawable.k1)
            1 -> rm.load(R.drawable.k2)
            2 -> rm.load(R.drawable.k3)
            3 -> rm.load(R.drawable.k4)
            4 -> rm.load(R.drawable.k5)
            5 -> rm.load(R.drawable.k6)
            6 -> rm.load(R.drawable.k7)
            7 -> rm.load(R.drawable.k8)
            8 -> rm.load(R.drawable.k9)
            9 -> rm.load(R.drawable.k10)
            10 -> rm.load(R.drawable.k11)
            11 -> rm.load(R.drawable.k12)
            else -> rm.load(R.drawable.k1)
        }

        rb.into(holder.postImageView)
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }
}