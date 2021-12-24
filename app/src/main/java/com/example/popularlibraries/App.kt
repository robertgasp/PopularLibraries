package com.example.popularlibraries

import android.app.Application
import com.example.popularlibraries.di.components.AppComponent
import com.example.popularlibraries.di.components.DaggerAppComponent
import com.example.popularlibraries.di.components.GithubReposSubcomponent
import com.example.popularlibraries.di.components.GithubUsersSubcomponent
import com.example.popularlibraries.di.modules.AppModule
import com.example.popularlibraries.di.scope.containers.ReposScopeContainer
import com.example.popularlibraries.di.scope.containers.UsersScopeContainer

class App : Application(), UsersScopeContainer, ReposScopeContainer {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.build()
            .appModule(AppModule(this))
            .build()
    }

    var usersSubcomponent: GithubUsersSubcomponent? = null
    var reposSubcomponent: GithubReposSubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    override fun initUsersSubcomponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    override fun destroyUsersSubcomponent() {
        usersSubcomponent = null
    }

    override fun initReposSubcomponent() =
        appComponent.usersSubcomponent().reposSubcomponent().also {
            reposSubcomponent = it
        }

    override fun destroyReposSubcomponent() {
        reposSubcomponent = null
    }

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}