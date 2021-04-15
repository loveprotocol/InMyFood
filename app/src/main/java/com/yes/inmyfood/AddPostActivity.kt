package com.yes.inmyfood

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
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
import com.yes.inmyfood.databinding.ActivityAddPostBinding

class AddPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPostBinding
    private var isBlockedScrollView = true
    private var outOfScroll: Boolean = false
    private var isPermission: Boolean = false
    private lateinit var galleryRecyclerViewAdapter:ImageCollectRecyclerViewAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        tedPermission()

        binding.scrollView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                return isBlockedScrollView
            }
        })

        GlobalBus.getBus().register(this)

        setSupportActionBar(binding.addPostToolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_x)
        }


        galleryRecyclerViewAdapter = ImageCollectRecyclerViewAdapter(Array(36) {"1"})
        binding.galleryImageList.adapter = galleryRecyclerViewAdapter


        val spacing = (resources.getDimension(R.dimen.recyclerview_grid_spacing) / resources.displayMetrics.density).toInt()
        binding.galleryImageList.setPadding(spacing, spacing, spacing, spacing)

        binding.galleryImageList.clipToPadding = false
        binding.galleryImageList.clipChildren = false
        binding.galleryImageList.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(spacing, spacing, spacing, spacing)
            }
        })
        binding.galleryImageList.setOnTouchListener { view, motionEvent ->
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
        val params = binding.imageToolbar.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
        binding.imageToolbar.layoutParams = params
        binding.galleryImageList.setOnTouchListener(null)
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
