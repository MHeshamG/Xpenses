package com.example.xpenses.view.recycler_view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.xpenses.model.PaymentsTotalCostOfDay
import com.example.xpenses.view.recycler_view.diff_callbacks.PaymentsTotalCostOfDayDiffCallback
import com.example.xpenses.view.recycler_view.view_holders.PaymentsOfDayViewHolder

class PaymentsOfDayInMonthAdapter:
    ListAdapter<PaymentsTotalCostOfDay, PaymentsOfDayViewHolder>(PaymentsTotalCostOfDayDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PaymentsOfDayViewHolder {
        return PaymentsOfDayViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: PaymentsOfDayViewHolder, position: Int) {
        val itemPayment = getItem(position)
        viewHolder.bind(itemPayment)
    }
}