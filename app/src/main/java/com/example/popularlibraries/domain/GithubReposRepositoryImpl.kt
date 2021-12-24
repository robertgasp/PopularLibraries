package com.example.popularlibraries.domain

import com.example.popularlibraries.db.cache.RoomGithubRepoCache
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.RetrofitService
import com.example.popularlibraries.remote.connection.NetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubReposRepositoryImpl @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val repoCache: RoomGithubRepoCache
) : GithubReposRepository {

    override fun getRepos(user: GithubUserModel): Single<List<GitHubReposModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(user.reposUrl)
                .flatMap(repoCache::insert)
        } else {
            repoCache.getReposOfTheSingleUser(user)
        }
    }
}