package com.example.popularlibraries.ui.singleUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibraries.App
import com.example.popularlibraries.databinding.FragmentSingleUserBinding
import com.example.popularlibraries.domain.GithubUserRepositoryImpl
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.remote.ApiHolder
import com.example.popularlibraries.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class SingleUserFragment(private val user: GithubUserModel) : MvpAppCompatFragment(),
    SingleUserView, BackButtonListener {

    private var _binding: FragmentSingleUserBinding? = null
    private val binding get() = _binding!!

/*    private val singleUserPresenter by moxyPresenter {
        SingleUserPresenter(
            App.instance.router,
            //GithubUserRepositoryImpl(ApiHolder.retrofitService)
        )
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.singleUserId.text = user.login

       // singleUserPresenter.backPressed()
    }

    override fun getLoginFromUsersList() {
        TODO("Not yet implemented")
    }

    override fun backPressed(): Boolean {
      //  singleUserPresenter.backPressed()
        return true
    }
}