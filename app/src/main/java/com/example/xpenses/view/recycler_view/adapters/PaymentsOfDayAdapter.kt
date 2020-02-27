package com.example.xpenses.view.recycler_view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.xpenses.model.PaymentsTotalCostOfDay
import com.example.xpenses.view.recycler_view.diff_callbacks.PaymentDiffCallback
import com.example.xpenses.view.recycler_view.diff_callbacks.PaymentsTotalCostOfDayDiffCallback
import com.example.xpenses.view.recycler_view.view_holders.PaymentViewHolder
import com.example.xpenses.view.recycler_view.view_holders.PaymentsOfDayViewHolder

class PaymentsOfDayAdapter (private val paymentItemClickListener: RecyclerAdapter.OnPaymentItemClickListener):
    ListAdapter<PaymentsTotalCostOfDay, PaymentsOfDayViewHolder>(PaymentsTotalCostOfDayDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PaymentsOfDayViewHolder {
        return PaymentsOfDayViewHolder.createPaymentsOfDayViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: PaymentsOfDayViewHolder, position: Int) {
        val itemPayment = getItem(position)
        viewHolder.bindPaymentsOfDay(itemPayment, paymentItemClickListener)
    }
}