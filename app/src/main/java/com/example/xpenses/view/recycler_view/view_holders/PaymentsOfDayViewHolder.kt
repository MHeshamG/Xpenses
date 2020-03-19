package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.PaymentsOfDayInMonthLayoutBinding
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.PaymentsDerivedInfo
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayInMonthAdapter

class PaymentsOfDayViewHolder private constructor(val binding: PaymentsOfDayInMonthLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun createViewHolder(parent: ViewGroup): PaymentsOfDayViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = PaymentsOfDayInMonthLayoutBinding.inflate(inflater, parent, false)
            return PaymentsOfDayViewHolder(inflatedView)
        }
    }

    fun bind(paymentsTotalCostOfDateOfDay: PaymentsDerivedInfo.PaymentsTotalCostOfDate, paymentItemClickListener: PaymentsOfDayInMonthAdapter.DayPaymentsClickListener) {
        binding.root.setOnClickListener { paymentItemClickListener.onDayPaymentsClick(DateFormater.getDayDateWithoutDayShortNameFormatFromMillis(paymentsTotalCostOfDateOfDay.date.time))}
        binding.dateOfPaymentsTextView.text = DateFormater.getFullDateTimeFromMillis(paymentsTotalCostOfDateOfDay.date.time)
        binding.totalCostOfPaymentsOfDay.text = paymentsTotalCostOfDateOfDay.totalCost.toString()+"$"
    }
}