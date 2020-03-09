package com.example.xpenses.model

import com.xpenses.model.PaymentType
import java.util.*


sealed class PaymentsDerivedInfo {

    abstract val id: Long

    data class PaymentsTotalCostOfDate(var date: Date, var totalCost: Double) : PaymentsDerivedInfo() {
        override val id: Long = 1
        lateinit var dateString:String
    }

    data class PaymentsCostDistributionAgainstType(val mapOfPaymentTypeAgainstCost: Map<PaymentType, Double>) :
        PaymentsDerivedInfo() {
        override val id: Long = 2
    }

    data class PaymentsTotalCostDistributionAgainstDaysInMonth(val listOfPaymentDayDateAgainstCost: List<Pair<Int, Double>>) :
        PaymentsDerivedInfo() {
        override val id: Long = 2
    }
}