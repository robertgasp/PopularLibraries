package com.example.popularlibraries.screens

import android.content.Intent
import com.example.popularlibraries.converterJpegToPng.ConverterJpegToPngActivity
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.ui.repos.ReposFragment
import com.example.popularlibraries.ui.singleRepo.SingleRepoFragment
import com.example.popularlibraries.ui.singleUser.SingleUserFragment
import com.example.popularlibraries.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    fun singleUserScreen(user: GithubUserModel) = FragmentScreen {
        SingleUserFragment(user)
    }

    fun reposScreen(userUrl:String) = FragmentScreen{
        ReposFragment(userUrl)
    }

    fun converterJpgToPng() = ActivityScreen {
        Intent(it, ConverterJpegToPngActivity::class.java)
    }

    fun singleRepoScreen(singleRepo:GitHubReposModel) = FragmentScreen{
        SingleRepoFragment(singleRepo)
    }
}