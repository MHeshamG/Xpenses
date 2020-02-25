package com.example.xpenses.view.recycler_view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.ui_data_models.DataItem
import com.example.xpenses.view.recycler_view.diff_callbacks.CarouselDiffCallback
import com.example.xpenses.view.recycler_view.view_holders.PaymentsChartViewHolder
import com.example.xpenses.view.recycler_view.view_holders.TotalPaymentsCostViewHolder
import java.lang.ClassCastException

class CarouselAdapter :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(CarouselDiffCallback()) {


    private val ITEM_VIEW_TYPE_TOTAL_COST = 0
    private val ITEM_VIEW_TYPE_COST_GRAPH = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_TOTAL_COST -> TotalPaymentsCostViewHolder.createCarouselItemHolder(parent)
            ITEM_VIEW_TYPE_COST_GRAPH -> PaymentsChartViewHolder.createPaymentsChartViewHolder(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TotalPaymentsCostViewHolder -> {
                val item = getItem(position) as DataItem.PaymentsCost
                holder.bind(item)
            }
            is PaymentsChartViewHolder -> {
                val item = getItem(position) as DataItem.PaymentsDistribution
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.PaymentsCost -> ITEM_VIEW_TYPE_TOTAL_COST
            is DataItem.PaymentsDistribution -> ITEM_VIEW_TYPE_COST_GRAPH
        }
    }
}