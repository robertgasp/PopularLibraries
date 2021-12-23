package com.example.popularlibraries.ui.imageLoading

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}