package com.example.mvisample.ktx

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context.applicationContext).load(url).into(this)
}