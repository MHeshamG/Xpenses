package com.example.xpenses.view.recycler_view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.TotalExpensesGraphBinding
import com.example.xpenses.databinding.TotalExpensesInfoLayoutBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import com.xwallet.business.PaymentType
import java.lang.ClassCastException

class CarouselAdapter : ListAdapter<CarouselAdapter.DataItem,RecyclerView.ViewHolder>(CarouselDiffCallback()) {


    private val ITEM_VIEW_TYPE_TOTAL_COST = 0
    private val ITEM_VIEW_TYPE_COST_GRAPH = 1



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_TOTAL_COST -> CarouselItemHolderPaymentsCost.createCarouselItemHolder(parent)
            ITEM_VIEW_TYPE_COST_GRAPH -> CarouselItemHolderPaymentsGraph.createCarouselItemHolderPaymentsGraph(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         when(holder){
            is CarouselItemHolderPaymentsCost ->{
                val item = getItem(position) as DataItem.PaymentsCost
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
            is DataItem.PaymentsCost ->ITEM_VIEW_TYPE_TOTAL_COST
            is DataItem.PaymentsDistrbution -> ITEM_VIEW_TYPE_COST_GRAPH
        }
    }

    class CarouselItemHolderPaymentsCost(
        val binding: TotalExpensesInfoLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun createCarouselItemHolder(parent: ViewGroup): CarouselItemHolderPaymentsCost {
                val inflater = LayoutInflater.from(parent.context)
                val inflatedView = TotalExpensesInfoLayoutBinding.inflate(inflater, parent, false)
                return CarouselItemHolderPaymentsCost(inflatedView)
            }
        }

        fun bind(totalCost: DataItem.PaymentsCost) {
            binding.totalCostValue.text = totalCost.totalCost.toString()
        }
    }

    class CarouselItemHolderPaymentsGraph(
        val binding: TotalExpensesGraphBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun createCarouselItemHolderPaymentsGraph(parent: ViewGroup): CarouselItemHolderPaymentsGraph {
                val inflater = LayoutInflater.from(parent.context)
                val inflatedView = TotalExpensesGraphBinding.inflate(inflater, parent, false)
                return CarouselItemHolderPaymentsGraph(inflatedView)
            }
        }

        fun bind(totalCost: DataItem.PaymentsDistrbution) {
           
        }
    }

    sealed class DataItem{

        abstract val id: Long
        data class PaymentsCost(val totalCost:Double): DataItem() {
            override val id: Long = 1
        }
        data class PaymentsDistrbution(val mapOfPaymentTypeAgainstCost:Map<PaymentType,Double>):DataItem(){
            override val id: Long = 2
        }
    }
}