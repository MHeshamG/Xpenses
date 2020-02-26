package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.R
import com.example.xpenses.databinding.TotalExpensesGraphBinding
import com.example.xpenses.ui_data_models.DataItem
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.components.Legend



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
        binding.chart1.setDrawEntryLabels(false)
        binding.chart1.description.isEnabled = false
        val legend =  binding.chart1.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.yOffset = -50f

    }

    private fun buildChartData(list: List<PieEntry>): PieData {
        val dataSet = PieDataSet(list, "Payments")
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()
        dataSet.valueTextSize = 8f
        dataSet.valueTextColor = ColorTemplate.rgb("#FFFFFF")

        return PieData(dataSet)
    }
}