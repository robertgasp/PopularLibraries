package com.example.popularlibraries.ui.singleUser

import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.domain.GithubUserRepositoryImpl
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.screens.AppScreens
import com.example.popularlibraries.ui.base.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class SingleUserPresenter(
    private val router: Router,
    private val userRepositoryImpl: GithubUserRepository
) : MvpPresenter<SingleUserView>() {

    private var user: GithubUserModel? = null

    private val backButtonListener: BackButtonListener? = null

    fun setUser(userClicked:GithubUserModel) {
        user = userClicked
        openFragment()
    }

    fun openFragment() {
        user?.let {
            router.navigateTo(AppScreens.singleUserScreen(it))
        }
    }

    fun backPressed(): Boolean {
        //backButtonListener?.backPressed()
        //router.backTo(AppScreens.usersScreen())
        return true
    }
}