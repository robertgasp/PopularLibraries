package com.example.popularlibraries.domain

import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.cache.RoomGithubUserCache
import com.example.popularlibraries.db.model.RoomGithubUser
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.RetrofitService
import com.example.popularlibraries.remote.connection.NetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GithubUserRepositoryImpl @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val usersCache: RoomGithubUserCache
) : GithubUserRepository {


    override fun getUsers(): Single<List<GithubUserModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getUsers()
                .flatMap(usersCache::insert)
        } else {
            usersCache.getUsers()
        }
    }
}