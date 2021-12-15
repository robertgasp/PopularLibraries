package com.example.popularlibraries.ui.users

import android.util.Log
import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.screens.AppScreens
import com.example.popularlibraries.ui.singleUser.SingleUserPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val router: Router,
    private val userRepository: GithubUserRepository
) : MvpPresenter<UsersView>() {

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
            }, { e ->
                Log.d("Retrofit", "Ошибка пользователя: $e")
                viewState.hideLoading()
            })
    }

    fun onUserClicked(userModel: GithubUserModel) {
        router.navigateTo(AppScreens.reposScreen(userModel.reposUrl))
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}