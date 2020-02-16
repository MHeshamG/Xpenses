package com.example.xpenses.view.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.xpenses.model.LeafPayment

class PaymentDiffCallback: DiffUtil.ItemCallback<LeafPayment>() {
    override fun areItemsTheSame(oldItem: LeafPayment, newItem: LeafPayment) = oldItem.paymentId == newItem.paymentId

    override fun areContentsTheSame(oldItem: LeafPayment, newItem: LeafPayment) = oldItem == newItem
}