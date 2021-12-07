package com.example.popularlibraries.screens

import android.view.View
import androidx.core.os.bundleOf
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.ui.singleUser.SingleUserFragment
import com.example.popularlibraries.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    fun singleUserScreen(user: GithubUserModel) = FragmentScreen {
        SingleUserFragment(user)
    }
}