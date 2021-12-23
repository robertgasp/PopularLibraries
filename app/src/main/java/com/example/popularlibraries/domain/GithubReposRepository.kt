package com.example.popularlibraries.domain

import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface GithubReposRepository {
    fun getRepos(login: GithubUserModel): Single<List<GitHubReposModel>>
}