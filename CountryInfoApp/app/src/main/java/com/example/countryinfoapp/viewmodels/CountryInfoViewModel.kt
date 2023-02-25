package com.example.countryinfoapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.countryinfoapp.CountryListQuery
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
class CountryInfoViewModel @Inject constructor(
    private val repository: CountryRepositoryImplementation
) : ViewModel() {
    private val _cList by lazy { MutableLiveData<ViewState<ApolloResponse<CountryListQuery.Data>>>() }
    val cList: LiveData<ViewState<ApolloResponse<CountryListQuery.Data>>>
        get() = _cList

    fun queryCountryList(context: Context) {
        viewModelScope.launch {
            _cList.postValue(ViewState.Loading())
            try {
                val response = repository.queryCountryList(context)
                _cList.postValue(ViewState.Success(response))
            } catch (e: ApolloException) {
                Log.e(APOLLO_TAG, context.getString(R.string.failure_exception_message), e)
                _cList.postValue(ViewState.Error(context.getString(R.string.view_state_error_message)))
            }
        }
    }
}
