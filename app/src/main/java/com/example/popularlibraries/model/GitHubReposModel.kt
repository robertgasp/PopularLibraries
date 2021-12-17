package com.example.popularlibraries.model

import com.google.gson.annotations.Expose

class GitHubReposModel(

    @Expose
    val id: String,

    @Expose
    val name: String,

    @Expose
    val owner: GithubRepoOwner,

    @Expose
    val forksCount: Int,

    @Expose
    val createdAt: String,

    @Expose
    val updatedAt: String
)

data class GithubRepoOwner(
    @Expose
    val id: String
)
