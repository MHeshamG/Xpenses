package com.example.xpenses.view.recycler_view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.xpenses.view.recycler_view.diff_callbacks.PaymentDiffCallback
import com.example.xpenses.view.recycler_view.view_holders.PaymentViewHolder
import com.xpenses.model.LeafPayment

class RecyclerAdapter(private val paymentItemClickListener: OnPaymentItemClickListener) :
    ListAdapter<LeafPayment, PaymentViewHolder>(PaymentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PaymentViewHolder {
        return PaymentViewHolder.createPaymentHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: PaymentViewHolder, position: Int) {
        val itemPayment = getItem(position)
        viewHolder.bindPayment(itemPayment,paymentItemClickListener)
    }

    interface OnPaymentItemClickListener{
        fun onPaymentItemClick(paymentId:Long)
    }

}