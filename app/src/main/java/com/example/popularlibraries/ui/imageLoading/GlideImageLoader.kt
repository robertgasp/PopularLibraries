package com.example.popularlibraries.ui.imageLoading

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader:ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .circleCrop()
            .into(container)
    }
}