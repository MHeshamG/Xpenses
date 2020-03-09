package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.TotalExpensesGraphLayoutBinding
import com.example.xpenses.model.PaymentsDerivedInfo
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.components.Legend



class PaymentsChartViewHolder(val binding: TotalExpensesGraphLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createViewHolder(parent: ViewGroup): PaymentsChartViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = TotalExpensesGraphLayoutBinding.inflate(inflater, parent, false)
            return PaymentsChartViewHolder(inflatedView)
        }
    }

    fun bind(paymentsCostDistributionAgainstType: PaymentsDerivedInfo.PaymentsCostDistributionAgainstType) {
        val list = paymentsCostDistributionAgainstType.mapOfPaymentTypeAgainstCost.map { paymentToCostEntry ->
            PieEntry(paymentToCostEntry.value.toFloat(), paymentToCostEntry.key.name)
        }
        binding.chart1.data = buildChartData(list)
        binding.chart1.animateXY(500, 500)
        binding.chart1.setDrawEntryLabels(false)
        binding.chart1.description.isEnabled = false
        val legend =  binding.chart1.legend
        setLegendProperties(legend)

    }

    private fun buildChartData(list: List<PieEntry>): PieData {
        val dataSet = PieDataSet(list, "Payments")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.asList()
        dataSet.valueTextSize = 8f
        dataSet.valueTextColor = ColorTemplate.rgb("#FFFFFF")

        return PieData(dataSet)
    }

    private fun setLegendProperties(legend: Legend) {
        legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.yOffset = -50f
    }
}