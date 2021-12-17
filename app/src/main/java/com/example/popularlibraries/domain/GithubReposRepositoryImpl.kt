package com.example.popularlibraries.domain

import com.example.popularlibraries.db.AppDataBase
import com.example.popularlibraries.db.model.RoomGithubRepo
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubRepoOwner
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.RetrofitService
import com.example.popularlibraries.remote.connection.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubReposRepositoryImpl(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val db: AppDataBase
) : GithubReposRepository {

    override fun getRepos(user: GithubUserModel): Single<List<GitHubReposModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(user.reposUrl)
                .flatMap { repos ->
                    Single.fromCallable {
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
                        db.repositoryDao.insert(dbRepos)
                        repos
                    }
                }
        } else {
            Single.fromCallable {
                db.repositoryDao.getByUserId(user.id)
                    .map {
                        GitHubReposModel(
                            it.id,
                            it.name,
                            GithubRepoOwner(it.id),
                            it.forksCount.toInt(),
                            it.createdAt,
                            it.updatedAt
                        )
                    }
            }
        }

    }
}