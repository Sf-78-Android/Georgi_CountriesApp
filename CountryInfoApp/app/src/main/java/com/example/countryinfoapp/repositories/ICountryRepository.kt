package com.example.countryinfoapp.repositories


import android.content.Context
import com.apollographql.apollo3.api.ApolloResponse
import com.example.countryinfoapp.CountryListQuery

interface ICountryRepository {
    suspend fun queryCountryList(context: Context): ApolloResponse<CountryListQuery.Data>
}