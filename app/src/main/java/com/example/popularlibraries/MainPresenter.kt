package com.example.popularlibraries

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(private val countersModel: CountersModel) : MvpPresenter<MainView>() {

    //private val model = CountersModel()

    fun counter1Click(id: Int) {
        val nextValue = countersModel.increment(id)
        viewState.setButton1Text(id, nextValue.toString())
    }

    fun counter2Click(id: Int) {
        val nextValue = countersModel.increment(id)
        viewState.setButton2Text(id, nextValue.toString())
    }

    fun counter3Click(id: Int) {
        val nextValue = countersModel.increment(id)
        viewState.setButton3Text(id, nextValue.toString())
    }
}