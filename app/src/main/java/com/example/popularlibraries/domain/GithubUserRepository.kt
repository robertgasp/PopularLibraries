package com.example.popularlibraries.domain

import com.example.popularlibraries.model.GithubUserModel
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import java.util.*

class GithubUserRepository {

    private val users = listOf(
        GithubUserModel("user1"),
        GithubUserModel("user2"),
        GithubUserModel("user3"),
        GithubUserModel("user4"),
        GithubUserModel("user5"),
        GithubUserModel("user6"),
    )

    fun getUsers(): @NonNull Observable<List<GithubUserModel>> {
        return Observable.just(users)
    }
}