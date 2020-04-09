package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.R
import com.example.xpenses.databinding.CurrenceyRateLayoutBinding
import com.example.xpenses.model.CurrencyRate

class CurrencyRateHolder private constructor(val binding: CurrenceyRateLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createViewHolder(parent: ViewGroup): CurrencyRateHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = CurrenceyRateLayoutBinding.inflate(inflater, parent, false)
            return CurrencyRateHolder(inflatedView)
        }
    }

    fun bind(currencyRate: CurrencyRate) {
        binding.countryFlag.setImageResource(getFlagImageResourceFromCountryName(currencyRate.country))
        binding.currencyRate.text = currencyRate.rate.toString()
    }

    private fun getFlagImageResourceFromCountryName(country: String): Int {
       return when(country){
            "EGP"-> R.drawable.icons8_egypt_100
            "EUR" -> R.drawable.icons8_flag_of_europe_100
           else -> R.drawable.icons8_egypt_100
       }
    }
}