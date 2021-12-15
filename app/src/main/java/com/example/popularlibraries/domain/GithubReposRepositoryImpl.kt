package com.example.popularlibraries.domain

import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.remote.RetrofitService
import io.reactivex.rxjava3.core.Single

class GithubReposRepositoryImpl(
    private val retrofitService: RetrofitService
) : GithubReposRepository {

    override fun getRepos(reposUrl: String): Single<List<GitHubReposModel>> {
        return retrofitService.getRepos(reposUrl)
    }
}