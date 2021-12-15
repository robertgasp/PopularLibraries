package com.example.popularlibraries.domain

import com.example.popularlibraries.model.GitHubReposModel
import io.reactivex.rxjava3.core.Single

interface GithubReposRepository {
    fun getRepos(login:String): Single<List<GitHubReposModel>>
}