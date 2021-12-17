package com.example.popularlibraries.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularlibraries.db.model.RoomGithubRepo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:RoomGithubRepo)

    @Query("SELECT * FROM RoomGithubRepo")
    fun getAll():List<RoomGithubRepo>

    @Query("SELECT * FROM RoomGithubRepo WHERE userId =:requestRepoById LIMIT 1" )
    fun getByUserId(requestRepoById:String):RoomGithubRepo
}