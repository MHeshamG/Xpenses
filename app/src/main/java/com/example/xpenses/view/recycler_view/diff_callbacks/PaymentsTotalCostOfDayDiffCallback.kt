package com.example.xpenses.view.recycler_view.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.xpenses.model.PaymentsDerivedInfo

class PaymentsTotalCostOfDayDiffCallback : DiffUtil.ItemCallback<PaymentsDerivedInfo.PaymentsTotalCostAndBudgetOfDate>() {
    override fun areItemsTheSame(
        oldItem: PaymentsDerivedInfo.PaymentsTotalCostAndBudgetOfDate,
        newItem: PaymentsDerivedInfo.PaymentsTotalCostAndBudgetOfDate
    ): Boolean = oldItem.date == newItem.date

    override fun areContentsTheSame(
        oldItem: PaymentsDerivedInfo.PaymentsTotalCostAndBudgetOfDate,
        newItem: PaymentsDerivedInfo.PaymentsTotalCostAndBudgetOfDate
    ): Boolean = oldItem == newItem
}