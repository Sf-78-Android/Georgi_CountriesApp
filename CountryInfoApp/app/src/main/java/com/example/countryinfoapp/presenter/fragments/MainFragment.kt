package com.example.countryinfoapp.presenter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.countryinfoapp.CountryListQuery
import com.example.countryinfoapp.adapters.CountryAdapter
import com.example.countryinfoapp.classes.ViewState
import com.example.countryinfoapp.constants.Constants.CODE
import com.example.countryinfoapp.constants.Constants.EMPTY_NUMBER
import com.example.countryinfoapp.databinding.FragmentMainBinding
import com.example.countryinfoapp.interfaces.OnClickCallback
import com.example.countryinfoapp.viewmodels.CountryInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var countryListVM: CountryInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryListVM = ViewModelProvider(this)[CountryInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        countryListVM.queryCountryList(requireContext())
    }

    private fun observeLiveData() {
        countryListVM.cList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.countryListRV.visibility = View.GONE
                }
                is ViewState.Success -> {
                    if (response.value?.data?.countries?.size == EMPTY_NUMBER) {
                        binding.countryListRV.visibility = View.GONE
                    } else {
                        binding.countryListRV.visibility = View.VISIBLE
                    }
                    val results = response.value?.data?.countries
                    getAdapter(results)
                }
                is ViewState.Error -> {
                    binding.countryListRV.visibility = View.GONE
                }
            }
        }
    }

    private fun getAdapter(list: List<CountryListQuery.Country>?) {
        val adapter = list?.let { adapter -> CountryAdapter(adapter) }

        adapter?.setOnItemClickListener(object : OnClickCallback {
            override fun onItemClick(countryList: CountryListQuery.Country) {
                val bundle = bundleOf(CODE to countryList.code)
            }

        })
        binding.countryListRV.adapter = adapter
    }
}