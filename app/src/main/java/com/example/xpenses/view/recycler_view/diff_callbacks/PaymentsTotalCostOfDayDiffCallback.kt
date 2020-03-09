package com.example.xpenses.view.recycler_view.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.xpenses.model.PaymentsDerivedInfo

class PaymentsTotalCostOfDayDiffCallback : DiffUtil.ItemCallback<PaymentsDerivedInfo.PaymentsTotalCostOfDate>() {
    override fun areItemsTheSame(
        oldItem: PaymentsDerivedInfo.PaymentsTotalCostOfDate,
        newItem: PaymentsDerivedInfo.PaymentsTotalCostOfDate
    ): Boolean = oldItem.date == newItem.date

    override fun areContentsTheSame(
        oldItem: PaymentsDerivedInfo.PaymentsTotalCostOfDate,
        newItem: PaymentsDerivedInfo.PaymentsTotalCostOfDate
    ): Boolean = oldItem == newItem
}