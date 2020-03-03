package com.example.xpenses.view.recycler_view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.xpenses.view.recycler_view.diff_callbacks.PaymentDiffCallback
import com.example.xpenses.view.recycler_view.view_holders.PaymentViewHolder
import com.example.xpenses.model.Payment

class PaymentsOfDayAdapter(private val paymentItemClickListener: OnPaymentItemClickListener) :
    ListAdapter<Payment, PaymentViewHolder>(PaymentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PaymentViewHolder {
        return PaymentViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: PaymentViewHolder, position: Int) {
        val itemPayment = getItem(position)
        viewHolder.bind(itemPayment,paymentItemClickListener)
    }

    interface OnPaymentItemClickListener{
        fun onPaymentItemClick(paymentId:Long)
    }

}