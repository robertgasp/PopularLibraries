package com.example.popularlibraries.ui.users

import android.util.Log
import com.example.popularlibraries.domain.GithubReposRepositoryImpl
import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.ApiHolder
import com.example.popularlibraries.screens.AppScreens
import com.example.popularlibraries.ui.main.repos.ReposPresenter
import com.example.popularlibraries.ui.singleUser.SingleUserPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val router: Router,
    private val userRepository: GithubUserRepository
) : MvpPresenter<UsersView>() {

    private var singleUserPresenter=SingleUserPresenter(router,userRepository)


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        userRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe({
                viewState.updateList(it)
                viewState.hideLoading()
            },{e->
                Log.d("Retrofit","Ошибка пользователя: $e")
                viewState.hideLoading()
            })
    }

    fun onUserClicked(userModel:GithubUserModel){
/*        val reposPresenter= ReposPresenter(router,GithubReposRepositoryImpl(ApiHolder.retrofitService))
        val repos = GitHubReposModel(userModel.login, userModel.avatarUrl,userModel.reposUrl)
        reposPresenter.setUser(userModel)
        reposPresenter.openReposList(repos)*/
       // singleUserPresenter.setUser(userModel)

        router.navigateTo(AppScreens.reposScreen(userModel.reposUrl))
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}