package com.example.shopeeapp.extension

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.gone() {
    this.visibility = GONE
}

fun ImageView.loadFromUrl(url: String) {
    if (url.isEmpty()) return

    Picasso.get().load(url).into(this)
}