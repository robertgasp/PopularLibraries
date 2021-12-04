package com.example.popularlibraries.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.App
import com.example.popularlibraries.databinding.FragmentUsersBinding
import com.example.popularlibraries.domain.GithubUserRepository
import com.example.popularlibraries.ui.base.BackButtonListener
import com.example.popularlibraries.ui.main.MainView
import com.example.popularlibraries.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        UserPresenter(
            App.instance.router,
            GithubUserRepository()
        )
    }
    private val adapter by lazy { UsersAdapter(presenter.usersListPresenter) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return (binding.root)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        usersRecycler.adapter = adapter
    }


    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }
}