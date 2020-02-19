package com.example.xpenses.view.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.PaymentLayoutBinding
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.PaymentTypeIconResourceMap
import com.xpenses.model.LeafPayment
import com.xwallet.business.PaymentType

class RecyclerAdapter(val paymentItemClickListener:OnPaymentItemClickListener) :
    ListAdapter<LeafPayment, RecyclerAdapter.PaymentHolder>(PaymentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PaymentHolder {
        return PaymentHolder.createPaymentHolder(parent)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val itemPayment = getItem(position)
        holder.bindPayment(itemPayment,paymentItemClickListener)
    }


    class PaymentHolder private constructor(val binding: PaymentLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        private var leafPayment: LeafPayment? = null

        companion object {
            fun createPaymentHolder(parent: ViewGroup): PaymentHolder {
                val inflater = LayoutInflater.from(parent.context)
                val inflatedView = PaymentLayoutBinding.inflate(inflater, parent, false)
                return PaymentHolder(inflatedView)
            }
        }

        fun bindPayment(leafPayment: LeafPayment, paymentItemClickListener:OnPaymentItemClickListener) {
            this.leafPayment = leafPayment
            binding.root.setOnClickListener {paymentItemClickListener.onPaymentItemClick(leafPayment.paymentId)};
            binding.paymentTypeText.text = PaymentType.fromInt(leafPayment.type).toString()
            binding.paymentCost.text = leafPayment.cost.toString() + "$"
            binding.paymentTime.text = DateFormater.getDateFromMillis(leafPayment.dateTime.time)
            binding.paymentTypeIcon.setBackgroundResource(PaymentTypeIconResourceMap[PaymentType.fromInt(leafPayment.type)]!!)
        }
    }

    interface OnPaymentItemClickListener{
        fun onPaymentItemClick(paymentId:Long)
    }

}