package com.example.popularlibraries.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.App
import com.example.popularlibraries.databinding.FragmentReposBinding
import com.example.popularlibraries.domain.GithubReposRepositoryImpl
import com.example.popularlibraries.model.GitHubReposModel
import com.example.popularlibraries.remote.ApiHolder
import com.example.popularlibraries.ui.base.BackButtonListener
import com.example.popularlibraries.ui.repos.adapter.RepoView
import com.example.popularlibraries.ui.repos.adapter.ReposAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ReposFragment(reposUrl: String) : MvpAppCompatFragment(), RepoView, BackButtonListener {

    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    private val repoPresenter by moxyPresenter {
        ReposPresenter(
            App.instance.router,
            GithubReposRepositoryImpl(ApiHolder.retrofitService),
            reposUrl
        )
    }


    private val repoAdapter by lazy { ReposAdapter(repoPresenter::onRepoClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        reposRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        reposRecyclerView.adapter = repoAdapter
    }

    override fun updateReposList(repos: List<GitHubReposModel>) {
        repoAdapter.submitList(repos)
    }

    override fun showLoading() {
        binding.loadingBar.isVisible = true
        binding.reposRecyclerView.isVisible = false
    }

    override fun hideLoading() {
        binding.loadingBar.isVisible = false
        binding.reposRecyclerView.isVisible = true
    }

    override fun backPressed(): Boolean {
        repoPresenter.backPressed()
        return true
    }

}