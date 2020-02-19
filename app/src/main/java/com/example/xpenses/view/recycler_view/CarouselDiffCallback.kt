package com.example.xpenses.view.recycler_view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.xpenses.model.LeafPayment

class CarouselDiffCallback : DiffUtil.ItemCallback<CarouselAdapter.DataItem>() {
    override fun areItemsTheSame(
        oldItem: CarouselAdapter.DataItem,
        newItem: CarouselAdapter.DataItem
    ) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: CarouselAdapter.DataItem,
        newItem: CarouselAdapter.DataItem
    ) = oldItem == newItem

}