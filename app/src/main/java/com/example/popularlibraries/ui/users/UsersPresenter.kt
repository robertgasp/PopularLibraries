package com.example.popularlibraries.ui.users

import android.util.Log
import com.example.popularlibraries.di.scope.containers.UsersScopeContainer
import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.screens.AppScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val router: Router,
    private val userRepository: GithubUserRepository,
    private val appScreens: AppScreens,
    private val usersScopeContainer: UsersScopeContainer,
) : MvpPresenter<UsersView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    override fun onDestroy() {
        usersScopeContainer.destroyUsersSubcomponent()
        super.onDestroy()
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
        router.navigateTo(appScreens.reposScreen(userModel))
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}