package com.example.popularlibraries.di.components

import com.example.popularlibraries.di.modules.GithubReposModule
import com.example.popularlibraries.di.scope.RepositoryScope
import com.example.popularlibraries.ui.repos.ReposPresenter
import com.example.popularlibraries.ui.repos.ReposPresenterFactory
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        GithubReposModule::class
    ]
)
interface GithubReposSubcomponent {

    fun reposPresenterFactory(): ReposPresenterFactory
}