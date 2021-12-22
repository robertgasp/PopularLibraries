package com.example.popularlibraries.di.components

import com.example.popularlibraries.di.modules.*
import com.example.popularlibraries.ui.main.MainActivity
import com.example.popularlibraries.ui.main.MainPresenter
import com.example.popularlibraries.ui.repos.ReposPresenter
import com.example.popularlibraries.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CacheModule::class,
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun mainPresenter(): MainPresenter

    fun usersPresenter(): UsersPresenter

    fun reposPresenter(): ReposPresenter

    fun inject(mainActivity: MainActivity)

}