package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.TotalExpensesCostLayoutBinding
import com.example.xpenses.ui_data_models.DataItem

class TotalPaymentsCostViewHolder(val binding: TotalExpensesCostLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createViewHolder(parent: ViewGroup): TotalPaymentsCostViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = TotalExpensesCostLayoutBinding.inflate(inflater, parent, false)
            return TotalPaymentsCostViewHolder(
                inflatedView
            )
        }
    }

    fun bind(totalCost: DataItem.PaymentsTotalCost) {
        binding.totalCostValue.text = totalCost.totalCost.toString()
    }
}