package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.PaymentsOfDaysInMonthGraphLayoutBinding
import com.example.xpenses.model.PaymentsDerivedInfo
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*

class PaymentsBarChartViewHolder(val binding: PaymentsOfDaysInMonthGraphLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createViewHolder(parent: ViewGroup): PaymentsBarChartViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView =
                PaymentsOfDaysInMonthGraphLayoutBinding.inflate(inflater, parent, false)
            return PaymentsBarChartViewHolder(inflatedView)
        }
    }

    fun bind(paymentsDistributionDistributionAgainstType: PaymentsDerivedInfo.PaymentsTotalCostDistributionAgainstDaysInMonth) {
        val list =
            paymentsDistributionDistributionAgainstType.listOfPaymentDayDateAgainstCost.map { paymentsTotalCostOfDay ->
                BarEntry(
                    paymentsTotalCostOfDay.first.toFloat(),
                    paymentsTotalCostOfDay.second.toFloat()
                )

            }
        binding.barchart.data = buildChartData(list)
        binding.barchart.data.barWidth = 0.1f

        val xAxisFormatter = DayAxisvalueFormatter()
        binding.barchart.xAxis.valueFormatter = xAxisFormatter
        binding.barchart.xAxis.setDrawGridLines(false)
        binding.barchart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.barchart.xAxis.granularity = 1f


        binding.barchart.axisRight.isEnabled = false

        binding.barchart.axisLeft.axisMinimum = 0f
        binding.barchart.axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)





        binding.barchart.description.isEnabled = false

        binding.barchart.invalidate()


    }

    private fun buildChartData(list: List<BarEntry>): BarData {
        val dataSet = BarDataSet(list, "Payments")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.asList()
        dataSet.valueTextSize = 8f
        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(dataSet)
        return BarData(dataSets)
    }

    class DayAxisvalueFormatter : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return value.toInt().toString()
        }
    }

}