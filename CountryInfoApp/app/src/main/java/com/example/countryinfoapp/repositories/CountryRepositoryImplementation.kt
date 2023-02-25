package com.example.countryinfoapp.repositories

import android.content.Context
import com.apollographql.apollo3.api.ApolloResponse
import com.example.countryinfoapp.CountryDetailsQuery
import com.example.countryinfoapp.CountryListQuery
import com.example.countryinfoapp.networking.ApolloApi
import javax.inject.Inject

class CountryRepositoryImplementation @Inject constructor(
    private val webService: ApolloApi
) : ICountryRepository {
    override suspend fun queryCountryList(context: Context): ApolloResponse<CountryListQuery.Data> {
        return webService.getInstance(context).query(CountryListQuery()).execute()
    }

    override suspend fun queryCountryDetails(
        context: Context,
        code: String
    ): ApolloResponse<CountryDetailsQuery.Data> {
        return webService.getInstance(context).query(CountryDetailsQuery(code)).execute()
    }
}