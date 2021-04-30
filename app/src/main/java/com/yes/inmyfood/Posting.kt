package com.yes.inmyfood

import android.graphics.drawable.Drawable

data class Posting(
    val uploadDateTime: Long,
    val eatDateTime: Long,
    //val foodImageURL: String,
    val foodDrawable: Drawable,
    val tagList: List<String>
)