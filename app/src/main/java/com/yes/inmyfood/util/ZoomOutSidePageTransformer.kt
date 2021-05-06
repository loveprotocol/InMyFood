package com.yes.inmyfood.util

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.yes.inmyfood.R
import kotlin.math.abs

class ZoomOutSidePageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            val offsetEitherSidePage = resources.getDimensionPixelOffset(R.dimen.offset_either_side_page)
            translationX = position * offsetEitherSidePage

            val scaleFactor = MIN_SCALE.coerceAtLeast(1 - abs(position))
            scaleY = scaleFactor
            alpha = scaleFactor

//            val tv = findViewById<TextView>(R.id.frag_postings_item_tv_tag)
//            Log.i("pageTransformer", "view index is ${tv.text}, position is $position, scaleFactor is $scaleFactor")
        }
    }

    companion object {
        private const val MIN_SCALE = 0.8f
    }
}