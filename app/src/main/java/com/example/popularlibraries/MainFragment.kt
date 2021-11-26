package com.example.popularlibraries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibraries.databinding.FragmentMainBinding

class MainFragment : Fragment(), MainView {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val presenter = MainPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return (binding.root)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        btnCounter1.setOnClickListener {
            presenter.counterClick(0)
        }

        btnCounter2.setOnClickListener {
            presenter.counterClick(1)
        }

        btnCounter3.setOnClickListener {
            presenter.counterClick(2)
        }
    }


    override fun setButtonText(id: Int, text: String) = when (id) {
        0 -> binding.btnCounter1.text = text
        1 -> binding.btnCounter2.text = text
        2 -> binding.btnCounter3.text = text
        else -> error("Неверный индекс")
    }
}