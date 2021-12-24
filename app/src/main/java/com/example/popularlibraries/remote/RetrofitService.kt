package com.example.popularlibraries.remote

import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitService {

    @GET ("/users")
    fun getUsers():Single<List<GithubUserModel>>

    @GET
    fun getRepos(@Url reposUrl:String):Single<List<GitHubReposModel>>

}