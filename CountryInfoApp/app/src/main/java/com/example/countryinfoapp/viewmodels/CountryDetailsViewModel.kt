package com.example.countryinfoapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.countryinfoapp.CountryDetailsQuery
import com.example.countryinfoapp.R
import com.example.countryinfoapp.classes.ViewState
import com.example.countryinfoapp.constants.Constants.APOLLO_TAG
import com.example.countryinfoapp.repositories.CountryRepositoryImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val repository: CountryRepositoryImplementation
) : ViewModel() {
    private val _cDetails by lazy { MutableLiveData<ViewState<ApolloResponse<CountryDetailsQuery.Data>>>() }
    val cDetails: LiveData<ViewState<ApolloResponse<CountryDetailsQuery.Data>>> = _cDetails

    fun queryCountryDetails(context: Context, code: String) = viewModelScope.launch {
        _cDetails.postValue(ViewState.Loading())
        try {
            val response = repository.queryCountryDetails(context, code)
            _cDetails.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.e(APOLLO_TAG, context.getString(R.string.failure_exception_message), e)
            _cDetails.postValue(ViewState.Error(context.getString(R.string.view_state_error_details_message)))
        }
    }
}