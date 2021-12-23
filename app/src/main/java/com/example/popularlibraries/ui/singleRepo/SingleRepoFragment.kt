package com.example.popularlibraries.ui.singleRepo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibraries.R
import com.example.popularlibraries.databinding.FragmentSingleRepoBinding
import com.example.popularlibraries.model.GitHubReposModel
import kotlin.math.sin


class SingleRepoFragment(
    private val singleRepo: GitHubReposModel
) : Fragment() {

    private var _binding: FragmentSingleRepoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleRepoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        repoTitle.text = singleRepo.name
        singleRepoForksCount.text = singleRepo.forksCount.toString()
        singleRepoCreationDate.text = singleRepo.createdAt
        singleRepoLastUpdateData.text = singleRepo.updatedAt
    }

}