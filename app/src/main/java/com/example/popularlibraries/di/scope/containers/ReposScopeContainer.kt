package com.example.popularlibraries.di.scope.containers

import com.example.popularlibraries.di.components.GithubReposSubcomponent

interface ReposScopeContainer {

    fun initReposSubcomponent(): GithubReposSubcomponent

    fun destroyReposSubcomponent()
}