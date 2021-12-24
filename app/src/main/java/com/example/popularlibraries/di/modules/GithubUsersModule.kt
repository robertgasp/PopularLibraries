package com.example.popularlibraries.di.modules

import com.example.popularlibraries.App
import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.cache.RoomGithubUserCache
import com.example.popularlibraries.di.scope.RepositoryScope
import com.example.popularlibraries.di.scope.UsersScope
import com.example.popularlibraries.di.scope.containers.ReposScopeContainer
import com.example.popularlibraries.di.scope.containers.UsersScopeContainer
import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.domain.GithubUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class GithubUsersModule {

    @UsersScope
    @Binds
    abstract fun bindUsersRepo(impl: GithubUserRepositoryImpl): GithubUserRepository


    companion object {

        @UsersScope
        @Provides
        fun userCache(db: AppDataBase): RoomGithubUserCache = RoomGithubUserCache(db)

        @RepositoryScope
        @Provides
        fun scopeContainer(app: App): UsersScopeContainer = app
    }
}