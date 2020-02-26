package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.DateTimeProvider
import com.example.xpenses.ui_data_models.DataItem
import com.xpenses.model.LeafPayment
import com.xpenses.model.PaymentType
import com.xpenses.room.PaymentDao

class MonthPaymentsFragmentViewModel (val paymentDao: PaymentDao, application: Application) :
AndroidViewModel(application) {

    val thisMonthPayments = paymentDao.getAllPaymentsBetweenDates(
        DateTimeProvider.getThisMonthStartDate(),
        DateTimeProvider.getNextMonthStartDate()
    )

    fun getPaymentsInfo(): MediatorLiveData<List<DataItem>> {
        val paymentsInfoLiveData = MediatorLiveData<List<DataItem>>()
        paymentsInfoLiveData.addSource(
            thisMonthPayments
        ) { payments ->
            run {
                val paymentsInfo = mutableListOf<DataItem>()
                paymentsInfo.add(createTotalPaymentsCostDataItem(payments))
                paymentsInfo.add(createPaymentsDistributionDataItem(payments))
                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        return paymentsInfoLiveData
    }

    private fun createTotalPaymentsCostDataItem(payments: List<LeafPayment>) =
        DataItem.PaymentsCost(payments.sumByDouble { it.cost })

    private fun createPaymentsDistributionDataItem(payments: List<LeafPayment>): DataItem.PaymentsDistribution {
        val mapOfPaymentsTypeAgainstCost = mutableMapOf<PaymentType, Double>()

        payments.iterator().forEach {
            val currentCost = mapOfPaymentsTypeAgainstCost[PaymentType.fromInt(it.type)] ?: 0.0
            mapOfPaymentsTypeAgainstCost[PaymentType.fromInt(it.type)!!] = it.cost + currentCost
        }

        return DataItem.PaymentsDistribution(mapOfPaymentsTypeAgainstCost)
    }
}