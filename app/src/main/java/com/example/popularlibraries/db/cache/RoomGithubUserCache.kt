package com.example.popularlibraries.db.cache

import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.model.RoomGithubUser
import com.example.popularlibraries.model.GithubUserModel
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomGithubUserCache @Inject constructor(private val db: AppDataBase) {

    fun insert(users: List<GithubUserModel>): Single<List<GithubUserModel>> {

        val roomUsers = users.map {
            RoomGithubUser(it.id, it.login, it.avatarUrl, it.reposUrl)
        }
        return db.userDao.insert(roomUsers).toSingle { users }
    }

    fun getUsers(): Single<List<GithubUserModel>> {
        return db.userDao.getAll().map { users ->
            users.map {
                GithubUserModel(it.id, it.login, it.avatarUrl, it.reposUrl)
            }
        }
    }
}