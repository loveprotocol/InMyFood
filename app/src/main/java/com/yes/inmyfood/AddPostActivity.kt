package com.yes.inmyfood

import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_add_post.*

class AddPostActivity : AppCompatActivity() {

    lateinit var galleryRecyclerAdapter:FeedRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        setSupportActionBar(add_post_toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_x)
        }


        galleryRecyclerAdapter = FeedRecyclerAdapter(Array(36) {"1"})
        gallery_image_list.adapter = galleryRecyclerAdapter


        val spacing = (resources.getDimension(R.dimen.recyclerview_grid_spacing) / resources.displayMetrics.density).toInt()
        gallery_image_list.setPadding(spacing, spacing, spacing, spacing)

        gallery_image_list.clipToPadding = false
        gallery_image_list.clipChildren = false
        gallery_image_list.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(spacing, spacing, spacing, spacing)
            }
        })

        gallery_image_list.setOnTouchListener { view, motionEvent ->
            val outRect = Rect()
            view.getHitRect(outRect)
            if (outRect.top > motionEvent.rawY) {
                val params = imageToolbar.layoutParams as AppBarLayout.LayoutParams
                params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                imageToolbar.layoutParams = params
                gallery_image_list.setOnTouchListener(null)
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_done, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home   -> {
                finish()
                return true
            }
            R.id.menu_item_done -> {}

        }

        return super.onOptionsItemSelected(item)
    }
}
