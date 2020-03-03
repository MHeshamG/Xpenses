package com.example.xpenses.view.recycler_view.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.xpenses.model.Payment

class PaymentDiffCallback: DiffUtil.ItemCallback<Payment>() {
    override fun areItemsTheSame(oldItem: Payment, newItem: Payment) = oldItem.paymentId == newItem.paymentId

    override fun areContentsTheSame(oldItem: Payment, newItem: Payment) = oldItem == newItem
}