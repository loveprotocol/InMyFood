package com.yes.inmyfood

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_add_post.*

class AddPostActivity : AppCompatActivity() {

    private var isBlockedScrollView = true
    private var outOfScroll: Boolean = false
    private var isPermission: Boolean = false
    private lateinit var galleryRecyclerAdapter:FeedRecyclerAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        tedPermission()

        scrollView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                return isBlockedScrollView
            }
        })

        GlobalBus.getBus().register(this)

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
                isBlockedScrollView = false
            }
            false
        }

    }

    private fun tedPermission() {
        // 권한 요청 리스너 생성
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                // 권한을 얻었으므로 갤러리 접근

            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                finish()
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setRationaleMessage(resources.getString(R.string.permission_rational_msg_for_image))
            .setDeniedMessage(resources.getString(R.string.permission_denied_msg))
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .check()
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

    override fun onDestroy() {
        super.onDestroy()

        GlobalBus.getBus().unregister(this)
    }

    @Subscribe
    fun outOfScroll(event:Events.Companion.EventOutOfRect) {
        val params = imageToolbar.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
        imageToolbar.layoutParams = params
        gallery_image_list.setOnTouchListener(null)
        outOfScroll = true
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        if (outOfScroll) {
//            return true
//        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        if (outOfScroll)
//            Log.d("YES", "i love eunsup!")
        return super.onTouchEvent(event)
    }
}
