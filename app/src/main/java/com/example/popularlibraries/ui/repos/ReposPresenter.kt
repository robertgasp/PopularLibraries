package com.example.popularlibraries.ui.repos

import android.util.Log
import com.example.popularlibraries.domain.GithubReposRepository
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.screens.AppScreens
import com.example.popularlibraries.ui.repos.adapter.RepoView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class ReposPresenter @Inject constructor(
    private val router: Router,
    private val reposRepository: GithubReposRepository,
    private val appScreens: AppScreens
) : MvpPresenter<RepoView>() {

    lateinit var userModel: GithubUserModel

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRepos()
    }


    private fun loadRepos() {
        reposRepository.getRepos(userModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe({ list ->
                viewState.updateReposList(list)
                viewState.hideLoading()
            }, { e ->
                Log.d("Repos Retrofit Error", "Ошибка запроса репозиториев $e")
                viewState.hideLoading()
            })
    }


    fun onRepoClick(repos: GitHubReposModel) {
        //router.navigateTo(AppScreens.singleRepoScreen(repos))
        router.navigateTo(appScreens.singleRepoScreen(repos))
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}