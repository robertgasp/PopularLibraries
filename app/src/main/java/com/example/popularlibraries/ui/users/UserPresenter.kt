package com.example.popularlibraries.ui.users

import android.view.View
import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.screens.AppScreens
import com.example.popularlibraries.ui.base.IListPresenter
import com.example.popularlibraries.ui.singleUser.SingleUserFragment
import com.example.popularlibraries.ui.singleUser.SingleUserPresenter
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val router: Router,
    private val userRepository: GithubUserRepository
) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()

        usersListPresenter.itemClickListener = {
            //router.navigateTo(AppScreens.singleUserScreen(it))
            val singleUserPresenter=SingleUserPresenter(router,userRepository)
            singleUserPresenter.setUser(it)
            singleUserPresenter.openFragment()
        }
    }

    private fun loadData() {
        val users = userRepository.getUsers()
        usersListPresenter.users.addAll(users)

        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }


    class UsersListPresenter : IListPresenter<UserItemView> {

        val users = mutableListOf<GithubUserModel>()

        override var itemClickListener: (pos:Int)->Unit = {}

        override fun getCount() = users.size


        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }
}