package com.example.popularlibraries.ui.main.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.databinding.ItemReposBinding
import com.example.popularlibraries.model.GitHubReposModel

class ReposAdapter(
    private val reposClickListener: (GitHubReposModel) -> Unit,
) : androidx.recyclerview.widget.ListAdapter<GitHubReposModel, ReposAdapter.ReposViewHolder>(
    ReposItemCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        return ReposViewHolder(
            ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.showRepos(currentList[position])
    }


    inner class ReposViewHolder(private val vb: ItemReposBinding) :
        RecyclerView.ViewHolder(vb.root) {
        fun showRepos(repo: GitHubReposModel) {
            vb.root.setOnClickListener { reposClickListener(repo) }
            vb.repoName.text = repo.name
        }
    }

    object ReposItemCallback : DiffUtil.ItemCallback<GitHubReposModel>() {

        override fun areItemsTheSame(
            oldItem: GitHubReposModel,
            newItem: GitHubReposModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: GitHubReposModel,
            newItem: GitHubReposModel
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}