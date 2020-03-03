package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.DateTimeProvider
import com.example.xpenses.model.PaymentsTotalCostOfDay
import com.example.xpenses.ui_data_models.DataItem
import com.example.xpenses.model.Payment
import com.xpenses.model.PaymentType
import com.example.xpenses.room.PaymentDao
import java.util.*

class MonthPaymentsFragmentViewModel(val paymentDao: PaymentDao, application: Application) :
    AndroidViewModel(application) {

    private val thisMonthPayments = paymentDao.getAllPaymentsBetweenDates(
        DateTimeProvider.getThisMonthStartDate(),
        DateTimeProvider.getNextMonthStartDate()
    )

    fun getDaysPayments(): MediatorLiveData<List<PaymentsTotalCostOfDay>> {
        val daysPayments = MediatorLiveData<List<PaymentsTotalCostOfDay>>()
        daysPayments.addSource(thisMonthPayments) { payments ->
            run {
                daysPayments.value =
                    createPaymentsOfDaysListFromIndividualPaymentsOfDayList(payments)
            }
        }
        return daysPayments
    }

    private fun createPaymentsOfDaysListFromIndividualPaymentsOfDayList(
        payments: List<Payment>
    ): List<PaymentsTotalCostOfDay> {
        val daysAgainstPaymentsMap = mutableMapOf<Date, PaymentsTotalCostOfDay>()
        val paymentsTotalCostOfDayList = mutableListOf<PaymentsTotalCostOfDay>()
        payments.forEach {
            val dayDate = DateTimeProvider.getDateFromDateTime(it.dateTime)
            if (daysAgainstPaymentsMap[dayDate] != null) {
                daysAgainstPaymentsMap[dayDate]!!.totalCost += it.cost
            } else {
                daysAgainstPaymentsMap[dayDate] = PaymentsTotalCostOfDay(dayDate, it.cost)
                paymentsTotalCostOfDayList.add(daysAgainstPaymentsMap[dayDate]!!)
            }
        }
        return paymentsTotalCostOfDayList
    }

    fun getPaymentsInfo(): MediatorLiveData<List<DataItem>> {
        val paymentsInfoLiveData = MediatorLiveData<List<DataItem>>()
        paymentsInfoLiveData.addSource(
            thisMonthPayments
        ) { payments ->
            run {
                val paymentsInfo = mutableListOf<DataItem>()
                paymentsInfo.add(createTotalPaymentsCostDataItem(payments))
                paymentsInfo.add(createPaymentsDistributionDataItem(payments))
                paymentsInfo.add(createPaymentsTotalCostAgainstDaysInMonth(payments))
                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        return paymentsInfoLiveData
    }

    private fun createPaymentsTotalCostAgainstDaysInMonth(payments: List<Payment>): DataItem.PaymentsTotalCostDistributionAgainstDaysInMonth {
        val list = createPaymentsOfDaysListFromIndividualPaymentsOfDayList(payments)
        return DataItem.PaymentsTotalCostDistributionAgainstDaysInMonth(list.map { paymentsTotalCostOfDay ->
            Pair(
                DateTimeProvider.getDayOfDateFromDateTime(paymentsTotalCostOfDay.dayDate),
                paymentsTotalCostOfDay.totalCost
            )

        })
    }

    private fun createTotalPaymentsCostDataItem(payments: List<Payment>) =
        DataItem.PaymentsTotalCost(payments.sumByDouble { it.cost })

    private fun createPaymentsDistributionDataItem(payments: List<Payment>): DataItem.PaymentsCostDistributionAgainstType {
        val mapOfPaymentsTypeAgainstCost = mutableMapOf<PaymentType, Double>()

        payments.iterator().forEach {
            val currentCost =
                mapOfPaymentsTypeAgainstCost[PaymentType.fromInt(it.type)] ?: 0.0
            mapOfPaymentsTypeAgainstCost[PaymentType.fromInt(it.type)!!] =
                it.cost + currentCost
        }

        return DataItem.PaymentsCostDistributionAgainstType(mapOfPaymentsTypeAgainstCost)
    }
}