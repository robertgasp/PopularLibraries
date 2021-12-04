package com.example.popularlibraries.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.databinding.ItemUserBinding
import com.example.popularlibraries.model.GithubUserModel
import com.example.popularlibraries.ui.users.UserItemView
import com.example.popularlibraries.ui.users.UserPresenter

class UsersAdapter(
    private val presenter: UserPresenter.UsersListPresenter
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener(this.pos) }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class UserViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override fun setLogin(login: String) {
            vb.tvLogin.text = login
        }

        override var pos: Int = -1
    }


    object UsersItemCallback : DiffUtil.ItemCallback<GithubUserModel>() {
        override fun areItemsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(
            oldItem: GithubUserModel,
            newItem: GithubUserModel
        ): Boolean {
            return oldItem.login == newItem.login
        }
    }
}