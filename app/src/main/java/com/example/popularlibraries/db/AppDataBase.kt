package com.example.popularlibraries.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularlibraries.App
import com.example.popularlibraries.db.dao.RepoDao
import com.example.popularlibraries.db.dao.UserDao
import com.example.popularlibraries.db.model.RoomGithubRepo
import com.example.popularlibraries.db.model.RoomGithubUser

@Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepo::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract val userDao: UserDao

    abstract val repositoryDao: RepoDao

    companion object {

        private const val DB_NAME = "database.db"

        val instance by lazy {
            Room.databaseBuilder(App.instance, AppDataBase::class.java, DB_NAME)
                //.fallbackToDestructiveMigration()
                .build()
        }

    }
}