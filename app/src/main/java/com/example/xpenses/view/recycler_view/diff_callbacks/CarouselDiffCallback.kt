package com.example.xpenses.view.recycler_view.diff_callbacks

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.xpenses.ui_data_models.DataItem

class CarouselDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(
        oldItem: DataItem,
        newItem: DataItem
    ) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DataItem,
        newItem: DataItem
    ) = oldItem == newItem

}