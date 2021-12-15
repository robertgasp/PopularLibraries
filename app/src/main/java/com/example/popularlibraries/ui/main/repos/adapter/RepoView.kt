package com.example.popularlibraries.ui.main.repos.adapter

import com.example.popularlibraries.model.GitHubReposModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface RepoView : MvpView {

    @AddToEndSingle
    fun updateReposList(repos: List<GitHubReposModel>)

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()
}