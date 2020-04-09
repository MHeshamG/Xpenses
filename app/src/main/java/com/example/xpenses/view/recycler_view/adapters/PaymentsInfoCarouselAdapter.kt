package com.example.xpenses.view.recycler_view.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.model.PaymentsDerivedInfo
import com.example.xpenses.view.recycler_view.diff_callbacks.CarouselDiffCallback
import com.example.xpenses.view.recycler_view.view_holders.PaymentsBarChartViewHolder
import com.example.xpenses.view.recycler_view.view_holders.PaymentsChartViewHolder
import com.example.xpenses.view.recycler_view.view_holders.TotalPaymentsCostViewHolder
import java.lang.ClassCastException

class PaymentsInfoCarouselAdapter:RecyclerView.Adapter< RecyclerView.ViewHolder>() {


    private val ITEM_VIEW_TYPE_TOTAL_COST = 0
    private val ITEM_VIEW_TYPE_COST_GRAPH = 1
    private val ITEM_VIEW_TYPE_COST_PER_DAY_GRAPH = 2

    var paymentsDerivedInfolist = listOf<PaymentsDerivedInfo>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

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
                val item = paymentsDerivedInfolist[position] as PaymentsDerivedInfo.PaymentsTotalCostOfDate
                holder.bind(item)
            }
            is PaymentsChartViewHolder -> {
                val item = paymentsDerivedInfolist[position] as PaymentsDerivedInfo.PaymentsCostDistributionAgainstType
                holder.bind(item)
            }
            is PaymentsBarChartViewHolder -> {
                val item = paymentsDerivedInfolist[position] as PaymentsDerivedInfo.PaymentsTotalCostDistributionAgainstDaysInMonth
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (paymentsDerivedInfolist[position]) {
            is PaymentsDerivedInfo.PaymentsTotalCostOfDate -> ITEM_VIEW_TYPE_TOTAL_COST
            is PaymentsDerivedInfo.PaymentsCostDistributionAgainstType -> ITEM_VIEW_TYPE_COST_GRAPH
            is PaymentsDerivedInfo.PaymentsTotalCostDistributionAgainstDaysInMonth -> ITEM_VIEW_TYPE_COST_PER_DAY_GRAPH
        }
    }

    override fun getItemCount(): Int {
        return paymentsDerivedInfolist.size
    }
}