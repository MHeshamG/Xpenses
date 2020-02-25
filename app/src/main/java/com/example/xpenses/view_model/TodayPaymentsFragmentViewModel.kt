package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.ui_data_models.DataItem
import com.xpenses.model.LeafPayment
import com.xpenses.model.PaymentType
import com.xpenses.room.PaymentDao
import java.util.*

class TodayPaymentsFragmentViewModel(val paymentDao: PaymentDao, application: Application) :
    AndroidViewModel(application) {

    val todayPayments = paymentDao.getAllPaymentsBetweenDates(getTodayDate(), getTomorrowDate())

    fun getPaymentsInfo(): MediatorLiveData<List<DataItem>> {
        val paymentsInfoLiveData = MediatorLiveData<List<DataItem>>()
        paymentsInfoLiveData.addSource(
            todayPayments
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

    private fun getTodayDate(): Date {
        val calendar = Calendar.getInstance()
        setDateParams(calendar)
        return calendar.time
    }

    private fun getTomorrowDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        setDateParams(calendar)
        return calendar.time
    }

    private fun setDateParams(calendar: Calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }
}