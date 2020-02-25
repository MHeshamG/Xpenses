package com.example.xpenses.ui_data_models

import com.xpenses.model.PaymentType


sealed class DataItem {

    abstract val id: Long

    data class PaymentsCost(val totalCost: Double) : DataItem() {
        override val id: Long = 1
    }

    data class PaymentsDistribution(val mapOfPaymentTypeAgainstCost: Map<PaymentType, Double>) :
        DataItem() {
        override val id: Long = 2
    }
}