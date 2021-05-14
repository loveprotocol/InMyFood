package com.yes.inmyfood.util

import android.content.Context
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import com.yes.inmyfood.R

class HashTag(val context: Context) : ClickableSpan() {
    interface ClickEventListener {
        fun onClickEvent(data: String)
    }

    private var clickEventListener: ClickEventListener? = null

    fun setOnClickEventListener(listener: ClickEventListener) {
        clickEventListener = listener
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = context.getColor(R.color.linkColor)
    }

    override fun onClick(widget: View) {
        val tv = widget as TextView
        val spanned = tv.text as Spanned
        val start = spanned.getSpanStart(this)
        val end = spanned.getSpanEnd(this)
        val theWord = spanned.subSequence(start + 1, end).toString()
        clickEventListener?.onClickEvent(theWord)
    }
}