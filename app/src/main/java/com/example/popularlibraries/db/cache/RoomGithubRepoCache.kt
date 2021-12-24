package com.example.popularlibraries.db.cache

import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.model.RoomGithubRepo
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubRepoOwner
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.ApiHolder.retrofitService
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomGithubRepoCache @Inject constructor(private val db: AppDataBase) {

    fun insert(repos: List<GitHubReposModel>): Single<List<GitHubReposModel>> {
        val dbRepos = repos.map {
            RoomGithubRepo(
                it.id,
                it.name,
                it.owner.id,
                it.forksCount.toString(),
                it.createdAt,
                it.updatedAt
            )
        }
        return db.repositoryDao.insert(dbRepos).toSingle { repos }
    }

    fun getReposOfTheSingleUser(userModel: GithubUserModel): Single<List<GitHubReposModel>> {
        return db.repositoryDao.getByUserId(userModel.id)
            .map {
                it.map { singleRepo ->
                    GitHubReposModel(
                        singleRepo.id,
                        singleRepo.name,
                        GithubRepoOwner(singleRepo.userId),//вот здесь я никак не могу передать singleRepo.userId
                        singleRepo.forksCount,
                        singleRepo.createdAt,
                        singleRepo.updatedAt
                    )
                }
            }
    }


}