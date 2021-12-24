package com.example.popularlibraries.screens

import android.content.Intent
import com.example.popularlibraries.App
import com.example.popularlibraries.converterJpegToPng.ConverterJpegToPngActivity
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.ui.repos.ReposFragment
import com.example.popularlibraries.ui.singleRepo.SingleRepoFragment
import com.example.popularlibraries.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens {

    fun usersScreen(): FragmentScreen

    fun reposScreen(userModel: GithubUserModel): FragmentScreen

    fun converterJpgToPng(): ActivityScreen

    fun singleRepoScreen(singleRepo: GitHubReposModel): FragmentScreen
}

class AppScreensImpl : AppScreens {

    override fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    override fun reposScreen(userModel: GithubUserModel) = FragmentScreen {
        ReposFragment.newInstance(userModel)
    }

    override fun converterJpgToPng() = ActivityScreen {
        Intent(it, ConverterJpegToPngActivity::class.java)
    }

    override fun singleRepoScreen(singleRepo: GitHubReposModel) = FragmentScreen {
        SingleRepoFragment(singleRepo)
    }
}