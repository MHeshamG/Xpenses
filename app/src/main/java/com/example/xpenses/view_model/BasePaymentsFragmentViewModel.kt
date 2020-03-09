package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.xpenses.model.Payment
import com.example.xpenses.room.PaymentDao
import com.example.xpenses.model.PaymentsDerivedInfo
import com.xpenses.model.PaymentType
import java.util.*

open class BasePaymentsFragmentViewModel(val paymentDao: PaymentDao, application: Application) :
    AndroidViewModel(application) {

    protected fun getAllPaymentsBetweenDates(startDate: Date, endDate:Date) = paymentDao.getAllPaymentsBetweenDates(startDate,endDate)

    protected fun createTotalPaymentsCostDataItem(payments: List<Payment>,date: Date,dateString:String): PaymentsDerivedInfo.PaymentsTotalCostOfDate {
        val paymentsTotalCost =  PaymentsDerivedInfo.PaymentsTotalCostOfDate(date, payments.sumByDouble { it.cost })
        paymentsTotalCost.dateString = dateString
        return paymentsTotalCost
    }

    protected fun createPaymentsDistributionDataItem(payments: List<Payment>): PaymentsDerivedInfo.PaymentsCostDistributionAgainstType {
        val mapOfPaymentsTypeAgainstCost = mutableMapOf<PaymentType, Double>()

        payments.iterator().forEach {
            val currentCost =
                mapOfPaymentsTypeAgainstCost[PaymentType.fromInt(it.type)] ?: 0.0
            mapOfPaymentsTypeAgainstCost[PaymentType.fromInt(it.type)!!] =
                it.cost + currentCost
        }

        return PaymentsDerivedInfo.PaymentsCostDistributionAgainstType(mapOfPaymentsTypeAgainstCost)
    }
}