package com.example.popularlibraries.ui.main

import com.example.popularlibraries.screens.AppScreens
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppScreen
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(AppScreens.usersScreen())
    }

    fun backPressed(){
        router.exit()
    }
}