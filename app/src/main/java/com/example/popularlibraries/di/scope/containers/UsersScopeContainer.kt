package com.example.popularlibraries.di.scope.containers

import com.example.popularlibraries.di.components.GithubUsersSubcomponent

interface UsersScopeContainer {

    fun initUsersSubcomponent(): GithubUsersSubcomponent

    fun destroyUsersSubcomponent()
}