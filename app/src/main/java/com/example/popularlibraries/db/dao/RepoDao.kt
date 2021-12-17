package com.example.popularlibraries.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularlibraries.db.model.RoomGithubRepo
import com.example.popularlibraries.model.GitHubReposModel

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:RoomGithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users:List<RoomGithubRepo>)

    @Query("SELECT * FROM RoomGithubRepo")
    fun getAll():List<RoomGithubRepo>

    @Query("SELECT * FROM RoomGithubRepo WHERE userId =:requestRepoById" )
    fun getByUserId(requestRepoById:String):List<GitHubReposModel>
}