package com.example.popularlibraries.ui.main.repos

import android.util.Log
import com.example.popularlibraries.domain.GithubReposRepository
import com.example.popularlibraries.domain.GithubReposRepositoryImpl
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.screens.AppScreens
import com.example.popularlibraries.ui.main.repos.adapter.RepoView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ReposPresenter(
    private val router: Router,
    private val reposRepository: GithubReposRepository,
    private val reposUrl:String,

):MvpPresenter<RepoView>() {

    private var user:GithubUserModel?=null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRepos()
    }

    fun setUser(newUser:GithubUserModel){
        user = newUser

    }

    private fun  loadRepos(){
            reposRepository.getRepos(reposUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list->
                    viewState.updateReposList(list)
                },{e->
                    Log.d("Repos Retrofit Error","Ошибка запроса репозиториев $e")
                })
    }

    fun openReposList(repos:GitHubReposModel){
        user?.let {
            router.navigateTo(AppScreens.reposScreen(repos.name))
        }
    }

    fun backPressed():Boolean{
        router.exit()
        return true
    }
}