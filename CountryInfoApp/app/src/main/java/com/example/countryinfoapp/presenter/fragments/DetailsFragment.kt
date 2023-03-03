package com.example.countryinfoapp.presenter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.countryinfoapp.R
import com.example.countryinfoapp.classes.ViewState
import com.example.countryinfoapp.classes.utilities.glideSetup
import com.example.countryinfoapp.constants.Constants.BUNDLE_TAG
import com.example.countryinfoapp.constants.Constants.CODE
import com.example.countryinfoapp.databinding.FragmentDetailsBinding
import com.example.countryinfoapp.viewmodels.CountryDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var countryDetailsVM: CountryDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryDetailsVM = ViewModelProvider(this)[CountryDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        requireArguments().getString(CODE)?.let { argumentString ->
            requireContext().getString(R.string.country_code).plus(argumentString).let {
                Log.d(BUNDLE_TAG, it)
            }

            binding.apply {
                countryDetailsVM.cDetails.observe(viewLifecycleOwner) { countryDetails ->
                    when (countryDetails) {
                        is ViewState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            countryDetailsNotFound.visibility = View.GONE
                        }

                        is ViewState.Success -> {
                            if (countryDetails.value?.data?.country == null) {
                                progressBar.visibility = View.VISIBLE
                                countryDetailsNotFound.visibility = View.VISIBLE
                            } else {
                                progressBar.visibility = View.GONE
                                countryDetailsNotFound.visibility = View.GONE
                            }
                            val results = countryDetails.value?.data?.country
                            results?.let { cDetails ->
                                countryTextView.text = cDetails.name
                                capitalTextView.text = cDetails.capital
                                regionTextView.text = cDetails.continent.name
                                currenciesTextView.text = cDetails.currency
                                officialLanguagesTextView.text = cDetails.native
                                phoneCodesTextView.text = cDetails.phone

                                glideSetup(flagImageView, cDetails)
                            }
                        }

                        is ViewState.Error -> {
                            progressBar.visibility = View.VISIBLE
                            countryDetailsNotFound.visibility = View.VISIBLE
                        }
                    }
                }
                countryDetailsVM.queryCountryDetails(requireContext(), argumentString)
            }
        }
    }
}