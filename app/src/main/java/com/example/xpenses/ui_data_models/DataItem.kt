package com.example.xpenses.ui_data_models

import com.xpenses.model.PaymentType


sealed class DataItem {

    abstract val id: Long

    data class PaymentsTotalCost(val totalCost: Double) : DataItem() {
        override val id: Long = 1
    }

    data class PaymentsCostDistributionAgainstType(val mapOfPaymentTypeAgainstCost: Map<PaymentType, Double>) :
        DataItem() {
        override val id: Long = 2
    }

    data class PaymentsTotalCostDistributionAgainstDaysInMonth(val listOfPaymentDayDateAgainstCost: List<Pair<Int, Double>>) :
        DataItem() {
        override val id: Long = 2
    }
}