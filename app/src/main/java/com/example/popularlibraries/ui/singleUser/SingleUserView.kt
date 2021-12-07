package com.example.popularlibraries.ui.singleUser

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface SingleUserView:MvpView {

    @AddToEndSingle
    fun getLoginFromUsersList()
}