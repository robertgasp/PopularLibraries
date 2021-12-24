package com.example.popularlibraries.di.modules

import com.example.popularlibraries.App
import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.cache.RoomGithubRepoCache
import com.example.popularlibraries.db.cache.RoomGithubUserCache
import com.example.popularlibraries.di.scope.RepositoryScope
import com.example.popularlibraries.di.scope.containers.ReposScopeContainer
import com.example.popularlibraries.domain.GithubReposRepository
import com.example.popularlibraries.domain.GithubReposRepositoryImpl
import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.domain.GithubUserRepositoryImpl
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.RetrofitService
import com.example.popularlibraries.remote.connection.NetworkStatus
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class GithubReposModule {


    @RepositoryScope
    @Binds
    abstract fun bindReposRepo(impl: GithubReposRepositoryImpl): GithubReposRepository

    companion object {
        @RepositoryScope
        @Provides
        fun repoCache(db: AppDataBase): RoomGithubRepoCache = RoomGithubRepoCache(db)

        @RepositoryScope
        @Provides
        fun scopeContainer(app: App): ReposScopeContainer = app
    }
}