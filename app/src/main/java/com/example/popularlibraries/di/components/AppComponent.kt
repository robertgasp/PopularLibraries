package com.example.popularlibraries.di.components

import com.example.popularlibraries.di.modules.*
import com.example.popularlibraries.ui.main.MainActivity
import com.example.popularlibraries.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DBModule::class,
        CiceroneModule::class,
        AppModule::class,
        NetworkModule::class,
        GithubReposModule::class
    ]
)
interface AppComponent {

    fun mainPresenter(): MainPresenter

    fun inject(mainActivity: MainActivity)
}