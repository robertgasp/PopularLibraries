package com.example.popularlibraries.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        foreignKeys = [ForeignKey(
                entity = RoomGithubUser::class,
                parentColumns = ["id"],
                childColumns = ["userId"],
                onDelete = ForeignKey.CASCADE
        )]
)
data class RoomGithubRepo(
        @PrimaryKey val id:String,
        val name: String,
        val userId:String,
        val forksCount: String,
        val createdAt: String,
        val updatedAt: String
)
