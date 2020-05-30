package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.xpenses.repository.RepositoryInterface
import com.example.xpenses.model.Payment
import com.example.xpenses.model.PaymentsDerivedInfo
import com.xpenses.model.PaymentType
import java.util.*

open class BasePaymentsFragmentViewModel(val paymentsRepository: RepositoryInterface, application: Application) :
    AndroidViewModel(application) {

    protected fun getAllPaymentsBetweenDates(startDate: Date, endDate:Date) = paymentsRepository.fetchAllPaymentsBetweenDates(startDate,endDate)

    protected fun createTotalPaymentsCostDataItem(payments: List<Payment>,date: Date): PaymentsDerivedInfo.PaymentsTotalCostAndBudgetOfDate {
        val paymentsTotalCost =  PaymentsDerivedInfo.PaymentsTotalCostAndBudgetOfDate(date)
        paymentsTotalCost.totalCost = payments.sumByDouble { it.cost }
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