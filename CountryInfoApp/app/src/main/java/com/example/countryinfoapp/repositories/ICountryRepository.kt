package com.example.countryinfoapp.repositories


import android.content.Context
import com.apollographql.apollo3.api.ApolloResponse
import com.example.countryinfoapp.CountryDetailsQuery
import com.example.countryinfoapp.CountryListQuery

interface ICountryRepository {
    suspend fun queryCountryList(context: Context): ApolloResponse<CountryListQuery.Data>
    suspend fun queryCountryDetails(context: Context, code: String): ApolloResponse<CountryDetailsQuery.Data>
}