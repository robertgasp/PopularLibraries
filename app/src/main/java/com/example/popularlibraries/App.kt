package com.example.popularlibraries

import android.app.Application
import com.example.popularlibraries.di.components.AppComponent
import com.example.popularlibraries.di.components.DaggerAppComponent
import com.example.popularlibraries.di.modules.ContextModule
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.internal.DaggerCollections

class App : Application() {

    val appComponent:AppComponent by lazy {
        DaggerAppComponent.build()
            .contextModule(ContextModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    companion object{
        private var _instance:App?=null
        val instance
            get() = _instance!!
    }
}