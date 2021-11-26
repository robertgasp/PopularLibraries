package com.example.popularlibraries

import androidx.annotation.IdRes

class MainPresenter(val view: MainView) {

    private val model = CountersModel()

    fun counterClick(id: Int) {
        val nextValue = model.increment(id)
        view.setButtonText(id, nextValue.toString())
    }
}