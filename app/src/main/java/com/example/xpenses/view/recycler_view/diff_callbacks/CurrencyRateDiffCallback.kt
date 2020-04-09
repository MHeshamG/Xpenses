package com.example.xpenses.view.recycler_view.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.xpenses.model.CurrencyRate

class CurrencyRateDiffCallback: DiffUtil.ItemCallback<CurrencyRate>() {
    override fun areItemsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate) = oldItem.country == newItem.country

    override fun areContentsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate) = oldItem == newItem
}