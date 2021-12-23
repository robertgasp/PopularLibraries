package com.example.popularlibraries.domain

import com.example.popularlibraries.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface GithubUserRepository {
    fun getUsers(): Single<List<GithubUserModel>>
}