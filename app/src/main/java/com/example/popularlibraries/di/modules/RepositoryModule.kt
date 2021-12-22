package com.example.popularlibraries.di.modules

import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.cache.RoomGithubRepoCache
import com.example.popularlibraries.db.cache.RoomGithubUserCache
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
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun usersRepo(
        networkStatus: NetworkStatus,
        retrofitService: RetrofitService,
        dataBase: RoomGithubUserCache
    ): GithubUserRepository {
        return GithubUserRepositoryImpl(networkStatus, retrofitService, dataBase)
    }

    @Singleton
    @Provides
    fun reposRepo(
        networkStatus: NetworkStatus,
        retrofitService: RetrofitService,
        dataBase: RoomGithubRepoCache
    ): GithubReposRepository {
        return GithubReposRepositoryImpl(networkStatus, retrofitService, dataBase)
    }


}