package com.example.xpenses.view.recycler_view.diff_callbacks

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.xpenses.model.PaymentsDerivedInfo

class CarouselDiffCallback : DiffUtil.ItemCallback<PaymentsDerivedInfo>() {
    override fun areItemsTheSame(
        oldItem: PaymentsDerivedInfo,
        newItem: PaymentsDerivedInfo
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: PaymentsDerivedInfo,
        newItem: PaymentsDerivedInfo
    ) = oldItem == newItem

}