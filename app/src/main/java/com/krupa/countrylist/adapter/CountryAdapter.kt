package com.krupa.countrylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krupa.countrylist.R
import com.krupa.countrylist.api.Country

class CountryAdapter(private var countries: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    fun setData(newCountries: List<Country>) {
        countries = newCountries
        notifyDataSetChanged()
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameRegionCodeTextView: TextView = itemView.findViewById(R.id.nameRegionCodeTextView)
        private val capitalTextView: TextView = itemView.findViewById(R.id.capitalTextView)

        fun bind(country: Country) {
            val nameRegionCode = "${country.name}, ${country.region}   ${country.code}"
            nameRegionCodeTextView.text = nameRegionCode
            capitalTextView.text = country.capital
        }
    }

}