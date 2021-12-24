package com.example.popularlibraries.di.components

import com.example.popularlibraries.di.modules.GithubUsersModule
import com.example.popularlibraries.di.scope.UsersScope
import com.example.popularlibraries.ui.users.UsersPresenter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [
        GithubUsersModule::class
    ]
)
interface GithubUsersSubcomponent {

    fun reposSubcomponent():GithubReposSubcomponent

    fun provideUsersPresenter(): UsersPresenter
}