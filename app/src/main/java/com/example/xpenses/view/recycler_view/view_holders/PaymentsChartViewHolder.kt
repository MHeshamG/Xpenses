package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.TotalExpensesGraphBinding
import com.example.xpenses.ui_data_models.DataItem
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class PaymentsChartViewHolder(
    val binding: TotalExpensesGraphBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createPaymentsChartViewHolder(parent: ViewGroup): PaymentsChartViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = TotalExpensesGraphBinding.inflate(inflater, parent, false)
            return PaymentsChartViewHolder(inflatedView)
        }
    }

    fun bind(paymentsDistribution: DataItem.PaymentsDistribution) {
        val list = paymentsDistribution.mapOfPaymentTypeAgainstCost.map { paymentToCostEntry ->
            PieEntry(paymentToCostEntry.value.toFloat(), paymentToCostEntry.key.name)
        }
        binding.chart1.data = buildChartData(list)
        binding.chart1.animateXY(500, 500)
    }

    private fun buildChartData(list: List<PieEntry>): PieData {
        val dataSet = PieDataSet(list, "Payments")
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()
        return PieData(dataSet)
    }
}