package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.PaymentLayoutBinding
import com.example.xpenses.databinding.PaymentsOfDayBinding
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.PaymentTypeIconResourceMap
import com.example.xpenses.model.PaymentsTotalCostOfDay
import com.example.xpenses.view.recycler_view.adapters.RecyclerAdapter
import com.xpenses.model.LeafPayment
import com.xpenses.model.PaymentType

class PaymentsOfDayViewHolder private constructor(val binding: PaymentsOfDayBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun createPaymentsOfDayViewHolder(parent: ViewGroup): PaymentsOfDayViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = PaymentsOfDayBinding.inflate(inflater, parent, false)
            return PaymentsOfDayViewHolder(inflatedView)
        }
    }

    fun bindPaymentsOfDay(paymentsTotalCostOfDay: PaymentsTotalCostOfDay,paymentItemClickListener: RecyclerAdapter.OnPaymentItemClickListener) {
        binding.dateOfPaymentsTextView.text = DateFormater.getDateFromMillis(paymentsTotalCostOfDay.dayDate.time)
        binding.totalCostOfPaymentsOfDay.text = paymentsTotalCostOfDay.totalCost.toString()
    }
}