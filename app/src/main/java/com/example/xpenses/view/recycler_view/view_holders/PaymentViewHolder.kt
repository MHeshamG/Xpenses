package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.PaymentLayoutBinding
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.PaymentTypeIconResourceMap
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayAdapter
import com.example.xpenses.model.Payment
import com.xpenses.model.PaymentType

class PaymentViewHolder private constructor(val binding: PaymentLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun createViewHolder(parent: ViewGroup): PaymentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = PaymentLayoutBinding.inflate(inflater, parent, false)
            return PaymentViewHolder(inflatedView)
        }
    }

    fun bind(payment: Payment, paymentItemClickListener: PaymentsOfDayAdapter.OnPaymentItemClickListener) {
        binding.root.setOnClickListener {paymentItemClickListener.onPaymentItemClick(payment.paymentId)}
        binding.paymentTypeText.text = PaymentType.fromInt(payment.type).toString()
        binding.paymentCost.text = payment.cost.toString() + "$"
        binding.paymentTime.text = DateFormater.getDateFromMillis(payment.dateTime.time)
        binding.paymentTypeIcon.setBackgroundResource(PaymentTypeIconResourceMap[PaymentType.fromInt(payment.type)]!!)
    }
}