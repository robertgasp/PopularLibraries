package com.example.popularlibraries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubUserModel(
    @Expose
    val id:String,

    @Expose
    val login: String,

    @Expose
    val avatarUrl:String,

    @Expose
    val reposUrl:String
):Serializable