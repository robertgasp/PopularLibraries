package com.example.popularlibraries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GithubUserModel(
    @Expose
    val login: String,

    @Expose
    val avatarUrl:String,

    @Expose
    val reposUrl:String
)