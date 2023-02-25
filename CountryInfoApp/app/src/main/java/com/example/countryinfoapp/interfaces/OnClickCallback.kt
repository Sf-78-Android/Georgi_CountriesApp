package com.example.countryinfoapp.interfaces

import com.example.countryinfoapp.CountryListQuery

interface OnClickCallback {
    fun onItemClick(countryList: CountryListQuery.Country)
}