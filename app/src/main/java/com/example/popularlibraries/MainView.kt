package com.example.popularlibraries

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle


interface MainView:MvpView {
    @AddToEndSingle
    fun setButton1Text(id: Int, text: String)

    @AddToEndSingle
    fun setButton2Text(id: Int, text: String)

    @AddToEndSingle
    fun setButton3Text(id: Int, text: String)
}