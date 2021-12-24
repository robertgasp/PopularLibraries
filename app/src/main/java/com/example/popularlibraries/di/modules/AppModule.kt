package com.example.popularlibraries.di.modules

import android.app.Application
import android.content.Context
import com.example.popularlibraries.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun context(): Context = app


    @Singleton
    @Provides
    fun app(): App = app
}