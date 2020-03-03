package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.PaymentsOfDayInMonthLayoutBinding
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.PaymentsTotalCostOfDay

class PaymentsOfDayViewHolder private constructor(val binding: PaymentsOfDayInMonthLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun createViewHolder(parent: ViewGroup): PaymentsOfDayViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = PaymentsOfDayInMonthLayoutBinding.inflate(inflater, parent, false)
            return PaymentsOfDayViewHolder(inflatedView)
        }
    }

    fun bind(paymentsTotalCostOfDay: PaymentsTotalCostOfDay) {
        binding.dateOfPaymentsTextView.text = DateFormater.getDateFromMillis(paymentsTotalCostOfDay.dayDate.time)
        binding.totalCostOfPaymentsOfDay.text = paymentsTotalCostOfDay.totalCost.toString()+"$"
    }
}