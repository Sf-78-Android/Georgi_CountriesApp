package com.example.countryinfoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfoapp.CountryListQuery
import com.example.countryinfoapp.classes.CountryDiffUtil
import com.example.countryinfoapp.classes.utilities.glideSetup
import com.example.countryinfoapp.databinding.CardListBinding
import com.example.countryinfoapp.interfaces.OnClickCallback

class CountryAdapter(
    private val countryList: List<CountryListQuery.Country> = listOf()
) : ListAdapter<CountryListQuery.Country, CountryAdapter.CountryViewHolder>(CountryDiffUtil()) {

    private var mListener: OnClickCallback? = null

    fun setOnItemClickListener(listener: OnClickCallback) {
        mListener = listener
    }

    inner class CountryViewHolder(
        private val binding: CardListBinding
        ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(list: CountryListQuery.Country) {
            binding.apply {
                countryText.text = list.name
                capitalText.text = list.capital
                regionText.text = list.continent.name

                glideSetup(flagImageView, list)
            }

            itemClickListenerSetup(itemView, countryList[adapterPosition])
        }
    }

    private fun itemClickListenerSetup(itemView: View, country: CountryListQuery.Country) {
        itemView.setOnClickListener {
            mListener?.onItemClick(country)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding: CardListBinding = CardListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position])
    }
}