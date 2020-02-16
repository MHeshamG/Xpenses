package com.example.xpenses.view.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.R
import com.example.xpenses.databinding.PaymentTypeIconLayoutBinding
import com.example.xpenses.model.PaymentTypeIconResourceList
import com.xwallet.business.PaymentType

class PaymentTypeIconsRecyclerAdapter(private val paymentTypeItemClickListener: OnPaymentTypeItemClickListener) :
    RecyclerView.Adapter<PaymentTypeIconsRecyclerAdapter.PaymentTypeHolder>() {



    override fun getItemCount() = PaymentTypeIconResourceList.size


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PaymentTypeHolder {
        return PaymentTypeHolder.createPaymentHolder(parent)
    }

    override fun onBindViewHolder(holder: PaymentTypeHolder, position: Int) {
        val itemPayment = PaymentTypeIconResourceList[position]
        holder.bindPayment(itemPayment, paymentTypeItemClickListener)
    }


    class PaymentTypeHolder private constructor(val binding: PaymentTypeIconLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        companion object {
            fun createPaymentHolder(parent: ViewGroup): PaymentTypeHolder {
                val inflater = LayoutInflater.from(parent.context)
                val inflatedView = PaymentTypeIconLayoutBinding.inflate(inflater, parent, false)
                return PaymentTypeHolder(inflatedView)
            }
        }

        fun bindPayment(
            iconType: Pair<PaymentType,Int>,
            paymentTypeItemClickListener: OnPaymentTypeItemClickListener
        ) {
            binding.iconImage.setBackgroundResource(iconType.second)
            binding.paymentTypeText.text = iconType.first.name
            binding.root.setOnClickListener{paymentTypeItemClickListener.onPaymentItemClick(iconType)}
        }
    }

    interface OnPaymentTypeItemClickListener {
        fun onPaymentItemClick(iconType:Pair<PaymentType,Int>)
    }
}