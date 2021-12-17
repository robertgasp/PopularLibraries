package com.example.popularlibraries.domain

import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.model.RoomGithubUser
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.RetrofitService
import com.example.popularlibraries.remote.connection.NetworkStatus
import io.reactivex.rxjava3.core.Single


class GithubUserRepositoryImpl(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val db: AppDataBase
) : GithubUserRepository {


    override fun getUsers(): Single<List<GithubUserModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getUsers()
                .flatMap {
                    Single.fromCallable {
                        val roomUsers = it.map {
                            RoomGithubUser(it.id, it.login, it.avatarUrl, it.reposUrl)
                        }
                        db.userDao.insert(roomUsers)
                        it
                    }
                }
        } else {
            return Single.fromCallable {
                db.userDao.getAll().map {
                    GithubUserModel(it.id, it.login, it.avatarUrl, it.reposUrl)
                }
            }
        }
    }
}