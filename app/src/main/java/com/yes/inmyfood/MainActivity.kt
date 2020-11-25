package com.yes.inmyfood

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var feedRecyclerAdapter:FeedRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_add_btn.setOnClickListener(this)

        feedRecyclerAdapter = FeedRecyclerAdapter(Array(12) {"1"})
        main_post_rv.adapter = feedRecyclerAdapter


        val spacing = (resources.getDimension(R.dimen.recyclerview_grid_spacing) / resources.displayMetrics.density).toInt()
        main_post_rv.setPadding(spacing, spacing, spacing, spacing)

        main_post_rv.clipToPadding = false
        main_post_rv.clipChildren = false
        main_post_rv.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(spacing, spacing, spacing, spacing)
            }
        })
    }

    override fun onClick(view: View?) {
        view?.let {
            when (view) {
                main_add_btn -> {
                    val intent = Intent(this, AddPostActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
