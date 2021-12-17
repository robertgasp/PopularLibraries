package com.example.popularlibraries.model

import com.google.gson.annotations.Expose

class GitHubReposModel(
    @Expose
    val name: String,

    @Expose
    val forksCount: Int,

    @Expose
    val createdAt: String,

    @Expose
    val updatedAt: String
) {
}