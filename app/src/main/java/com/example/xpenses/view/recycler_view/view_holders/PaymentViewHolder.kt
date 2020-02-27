package com.example.xpenses.view.recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.PaymentLayoutBinding
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.PaymentTypeIconResourceMap
import com.example.xpenses.view.recycler_view.adapters.RecyclerAdapter
import com.xpenses.model.LeafPayment
import com.xpenses.model.PaymentType

class PaymentViewHolder private constructor(val binding: PaymentLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun createPaymentHolder(parent: ViewGroup): PaymentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val inflatedView = PaymentLayoutBinding.inflate(inflater, parent, false)
            return PaymentViewHolder(inflatedView)
        }
    }

    fun bindPayment(leafPayment: LeafPayment, paymentItemClickListener: RecyclerAdapter.OnPaymentItemClickListener) {
        binding.root.setOnClickListener {paymentItemClickListener.onPaymentItemClick(leafPayment.paymentId)};
        binding.paymentTypeText.text = PaymentType.fromInt(leafPayment.type).toString()
        binding.paymentCost.text = leafPayment.cost.toString() + "$"
        binding.paymentTime.text = DateFormater.getDateFromMillis(leafPayment.dateTime.time)
        binding.paymentTypeIcon.setBackgroundResource(PaymentTypeIconResourceMap[PaymentType.fromInt(leafPayment.type)]!!)
    }
}