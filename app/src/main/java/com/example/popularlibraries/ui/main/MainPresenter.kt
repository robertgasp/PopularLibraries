package com.example.popularlibraries.ui.main

import com.example.popularlibraries.screens.AppScreens
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppScreen
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(appScreens.usersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}