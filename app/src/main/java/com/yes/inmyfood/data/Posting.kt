package com.yes.inmyfood.data

import android.graphics.drawable.Drawable

data class Posting(
    val uploadDateTime: Long,
    val eatDateTime: Long,
    //val foodImageURL: String,
    val foodDrawable: Drawable,
    val tagList: List<String>
)