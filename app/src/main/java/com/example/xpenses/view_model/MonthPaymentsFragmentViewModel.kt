package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.DateTimeProvider
import com.example.xpenses.RepositoryInterface
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.Payment
import com.example.xpenses.model.PaymentsDerivedInfo
import java.util.*

class MonthPaymentsFragmentViewModel(paymentsRepository: RepositoryInterface, application: Application) :
    BasePaymentsFragmentViewModel(paymentsRepository, application) {

    private val thisMonthPayments = getAllPaymentsBetweenDates(
        DateTimeProvider.getThisMonthStartDate(),
        DateTimeProvider.getNextMonthStartDate()
    )

    private val monthDate = DateTimeProvider.getThisMonthDate()

    fun fetchDaysPayments(): MediatorLiveData<List<PaymentsDerivedInfo.PaymentsTotalCostOfDate>> {
        val daysPayments = MediatorLiveData<List<PaymentsDerivedInfo.PaymentsTotalCostOfDate>>()
        daysPayments.addSource(thisMonthPayments) { payments ->
            run {
                daysPayments.value = createPaymentsOfDaysListFromIndividualPaymentsOfDayList(payments)
            }
        }
        return daysPayments
    }

    fun fetchPaymentsDerivedInfo(): MediatorLiveData<List<PaymentsDerivedInfo>> {
        val paymentsInfoLiveData = MediatorLiveData<List<PaymentsDerivedInfo>>()
        paymentsInfoLiveData.addSource(
            thisMonthPayments
        ) { payments ->
            run {
                val paymentsInfo = mutableListOf<PaymentsDerivedInfo>()
                paymentsInfo.add(createTotalPaymentsCostDataItem(payments, monthDate,DateFormater.getMonthDateFormatFromMillis(monthDate.time)))
                paymentsInfo.add(createPaymentsDistributionDataItem(payments))
                paymentsInfo.add(createPaymentsTotalCostAgainstDaysInMonth(payments))
                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        return paymentsInfoLiveData
    }

    private fun createPaymentsTotalCostAgainstDaysInMonth(payments: List<Payment>): PaymentsDerivedInfo.PaymentsTotalCostDistributionAgainstDaysInMonth {
        val list = createPaymentsOfDaysListFromIndividualPaymentsOfDayList(payments)
        return PaymentsDerivedInfo.PaymentsTotalCostDistributionAgainstDaysInMonth(list.map { paymentsTotalCostOfDay ->
            Pair(
                DateTimeProvider.getDayOfDateFromDateTime(paymentsTotalCostOfDay.date),
                paymentsTotalCostOfDay.totalCost
            )
        })
    }

    private fun createPaymentsOfDaysListFromIndividualPaymentsOfDayList(payments: List<Payment>)
            : List<PaymentsDerivedInfo.PaymentsTotalCostOfDate> {
        val daysAgainstPaymentsMap = mutableMapOf<Date, PaymentsDerivedInfo.PaymentsTotalCostOfDate>()
        val paymentsTotalCostOfDayList = mutableListOf<PaymentsDerivedInfo.PaymentsTotalCostOfDate>()
        payments.forEach {
            val dayDate = DateTimeProvider.getDateFromDateTime(it.dateTime)
            if (daysAgainstPaymentsMap[dayDate] != null) {
                daysAgainstPaymentsMap[dayDate]!!.totalCost += it.cost
            } else {
                val paymentTotalCost = PaymentsDerivedInfo.PaymentsTotalCostOfDate(dayDate)
                paymentTotalCost.totalCost = it.cost
                daysAgainstPaymentsMap[dayDate] = paymentTotalCost
                paymentsTotalCostOfDayList.add(daysAgainstPaymentsMap[dayDate]!!)
            }
        }
        return paymentsTotalCostOfDayList
    }
}