package com.yes.inmyfood

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView

class FirstEndItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        val offset: Int = (getScreenWidth() / 2) - (view.layoutParams.width / 2)

        if (position == 0) {
            (view.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = 0
            outRect.left = offset
        } else if (position == itemCount -1) {
            (view.layoutParams as ViewGroup.MarginLayoutParams).rightMargin = 0
            outRect.right = offset
        }
    }

    private fun getScreenWidth() : Int {
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val size: Point = Point()
        display.getSize(size)
        return size.x
    }
}