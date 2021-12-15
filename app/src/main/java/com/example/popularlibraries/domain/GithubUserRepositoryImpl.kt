package com.example.popularlibraries.domain

import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.RetrofitService
import io.reactivex.rxjava3.core.Single


class GithubUserRepositoryImpl(
    private val retrofitService: RetrofitService
) : GithubUserRepository {


    override fun getUsers(): Single<List<GithubUserModel>> {
        return retrofitService.getUsers()
    }


}