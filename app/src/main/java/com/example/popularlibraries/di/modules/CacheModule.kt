package com.example.popularlibraries.di.modules

import android.content.Context
import androidx.room.Room
import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.cache.RoomGithubRepoCache
import com.example.popularlibraries.db.cache.RoomGithubUserCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class CacheModule {

    @Singleton
    @Provides
    fun db(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun userCache(db: AppDataBase): RoomGithubUserCache {
        return RoomGithubUserCache(db)
    }

    @Singleton
    @Provides
    fun repoCache(db: AppDataBase): RoomGithubRepoCache {
        return RoomGithubRepoCache(db)
    }
}