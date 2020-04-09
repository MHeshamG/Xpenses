package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.TotalExpensesCostLayoutBinding
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.PaymentsDerivedInfo

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

    fun bind(totalCostOfDate: PaymentsDerivedInfo.PaymentsTotalCostOfDate) {
        binding.totalCostValue.text = totalCostOfDate.totalCost.toString()
        binding.dateText.text = totalCostOfDate.dateString
        binding.dayBudgetTextView.text = totalCostOfDate.dayBudget?.toString()
    }
}