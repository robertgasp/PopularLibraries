package com.example.popularlibraries.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularlibraries.db.model.RoomGithubRepo
import com.example.popularlibraries.model.GitHubReposModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubRepo): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubRepo>): Completable

    @Query("SELECT * FROM RoomGithubRepo")
    fun getAll(): Single<List<RoomGithubRepo>>

    @Query("SELECT * FROM RoomGithubRepo WHERE userId =:requestRepoById")
    fun getByUserId(requestRepoById: String): Single<List<GitHubReposModel>>
}