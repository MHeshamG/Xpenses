package com.example.xpenses.view.recycler_view.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.ui_data_models.DataItem
import com.example.xpenses.view.recycler_view.diff_callbacks.CarouselDiffCallback
import com.example.xpenses.view.recycler_view.view_holders.PaymentsBarChartViewHolder
import com.example.xpenses.view.recycler_view.view_holders.PaymentsChartViewHolder
import com.example.xpenses.view.recycler_view.view_holders.TotalPaymentsCostViewHolder
import java.lang.ClassCastException

class PaymentsInfoCarouselAdapter :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(CarouselDiffCallback()) {


    private val ITEM_VIEW_TYPE_TOTAL_COST = 0
    private val ITEM_VIEW_TYPE_COST_GRAPH = 1
    private val ITEM_VIEW_TYPE_COST_PER_DAY_GRAPH = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_TOTAL_COST -> TotalPaymentsCostViewHolder.createViewHolder(parent)
            ITEM_VIEW_TYPE_COST_GRAPH -> PaymentsChartViewHolder.createViewHolder(parent)
            ITEM_VIEW_TYPE_COST_PER_DAY_GRAPH -> PaymentsBarChartViewHolder.createViewHolder(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TotalPaymentsCostViewHolder -> {
                val item = getItem(position) as DataItem.PaymentsTotalCost
                holder.bind(item)
            }
            is PaymentsChartViewHolder -> {
                Log.d("xxx",getItem(position).toString())
                val item = getItem(position) as DataItem.PaymentsCostDistributionAgainstType
                holder.bind(item)
            }
            is PaymentsBarChartViewHolder -> {
                val item = getItem(position) as DataItem.PaymentsTotalCostDistributionAgainstDaysInMonth
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.PaymentsTotalCost -> ITEM_VIEW_TYPE_TOTAL_COST
            is DataItem.PaymentsCostDistributionAgainstType -> ITEM_VIEW_TYPE_COST_GRAPH
            is DataItem.PaymentsTotalCostDistributionAgainstDaysInMonth -> ITEM_VIEW_TYPE_COST_PER_DAY_GRAPH
        }
    }
}