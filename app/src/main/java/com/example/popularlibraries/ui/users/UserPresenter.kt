package com.example.popularlibraries.ui.users

import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.ui.singleUser.SingleUserPresenter
import com.github.terrakok.cicerone.Router
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
            .subscribe{
                viewState.updateList(it)
            }
    }

    fun onUserClicked(userModel:GithubUserModel){
        singleUserPresenter.setUser(userModel)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}