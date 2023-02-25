package com.example.countryinfoapp.classes

import androidx.recyclerview.widget.DiffUtil
import com.example.countryinfoapp.CountryListQuery

class CountryDiffUtil : DiffUtil.ItemCallback<CountryListQuery.Country>() {
    override fun areItemsTheSame(
        oldItem: CountryListQuery.Country,
        newItem: CountryListQuery.Country
    ): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(
        oldItem: CountryListQuery.Country,
        newItem: CountryListQuery.Country
    ): Boolean {
        return oldItem == newItem
    }
}