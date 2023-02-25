package com.example.countryinfoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countryinfoapp.CountryListQuery
import com.example.countryinfoapp.classes.CountryDiffUtil
import com.example.countryinfoapp.constants.Constants.FLAGS_LINK
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

                Glide.with(flagImageView)
                    .load(String.format(FLAGS_LINK, list.code.lowercase()))
                    .into(flagImageView)
            }
        }

        init {
            //Setting the new interface listener here, using it's position parameter
            itemView.setOnClickListener {
                mListener?.onItemClick(countryList[adapterPosition])
            }
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