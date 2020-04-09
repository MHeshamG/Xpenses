package com.example.xpenses.view.recycler_view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.xpenses.model.CurrencyRate
import com.example.xpenses.view.recycler_view.diff_callbacks.CurrencyRateDiffCallback
import com.example.xpenses.view.recycler_view.view_holders.CurrencyRateHolder

class CurrencyRateAdapter: ListAdapter<CurrencyRate, CurrencyRateHolder>(CurrencyRateDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateHolder {
        return CurrencyRateHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CurrencyRateHolder, position: Int) {
        holder.bind(getItem(position))
    }
}