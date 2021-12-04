package com.example.popularlibraries.ui.singleUser

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.screens.AppScreens
import com.example.popularlibraries.ui.base.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

class SingleUserPresenter(
    private val router: Router,
    private val userRepository: GithubUserRepository
) : MvpPresenter<SingleUserView>() {

    private val users = userRepository.getUsers()
    private var user: GithubUserModel? = null

    private val backButtonListener: BackButtonListener? = null

    fun setUser(pos: Int) {
        user = users[pos]
    }

    fun openFragment() {
        user?.let {
            router.navigateTo(AppScreens.singleUserScreen(it))
        }
    }

    fun backPressed(): Boolean {
        //backButtonListener?.backPressed()
        // router.backTo(AppScreens.usersScreen())
        return true
    }
}