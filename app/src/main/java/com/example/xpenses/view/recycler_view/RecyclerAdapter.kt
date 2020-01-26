package com.example.xpenses.view.recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.databinding.PaymentLayoutBinding
import com.xwallet.business.LeafPayment
import kotlinx.android.synthetic.main.payment_layout.view.*

class RecyclerAdapter :
    ListAdapter<LeafPayment, RecyclerAdapter.PaymentHolder>(PaymentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PaymentHolder {
        return PaymentHolder.createPaymentHolder(parent)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val itemPayment = getItem(position)
        holder.bindPayment(itemPayment)
    }


    class PaymentHolder private constructor(val binding: PaymentLayoutBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private var leafPayment: LeafPayment? = null


        init {
            binding.root.setOnClickListener(this)
        }

        companion object {
            fun createPaymentHolder(parent: ViewGroup): PaymentHolder {
                val inflater = LayoutInflater.from(parent.context)
                val inflatedView = PaymentLayoutBinding.inflate(inflater, parent, false)
                return PaymentHolder(inflatedView)
            }
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindPayment(leafPayment: LeafPayment) {
            this.leafPayment = leafPayment
            binding.paymentTypeText.text = leafPayment.type.toString()
            binding.paymentCost.text = leafPayment.cost.toString() + "$"
            binding.paymentTime.text = leafPayment.dateTime.toString()
        }
    }


}