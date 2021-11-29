package com.example.popularlibraries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibraries.databinding.FragmentMainBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainFragment : MvpAppCompatFragment(), MainView {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    /*    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }*/

    @InjectPresenter
    lateinit var presenter : MainPresenter

    @ProvidePresenter
    fun ProvidePresenter():MainPresenter{
        return MainPresenter(CountersModel())
    }




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
            presenter.counter1Click(0)
        }

        btnCounter2.setOnClickListener {
            presenter.counter2Click(1)
        }

        btnCounter3.setOnClickListener {
            presenter.counter3Click(2)
        }
    }

    override fun setButton1Text(id: Int, text: String) {
        binding.btnCounter1.text = text
    }

    override fun setButton2Text(id: Int, text: String) {
        binding.btnCounter2.text = text
    }

    override fun setButton3Text(id: Int, text: String) {
        binding.btnCounter3.text = text
    }
}