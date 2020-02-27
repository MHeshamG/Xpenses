package com.example.xpenses.view.recycler_view.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.xpenses.model.PaymentsTotalCostOfDay
import com.xpenses.model.LeafPayment

class PaymentsTotalCostOfDayDiffCallback : DiffUtil.ItemCallback<PaymentsTotalCostOfDay>() {
    override fun areItemsTheSame(
        oldItem: PaymentsTotalCostOfDay,
        newItem: PaymentsTotalCostOfDay
    ): Boolean = oldItem.dayDate == newItem.dayDate

    override fun areContentsTheSame(
        oldItem: PaymentsTotalCostOfDay,
        newItem: PaymentsTotalCostOfDay
    ): Boolean = oldItem == newItem
}