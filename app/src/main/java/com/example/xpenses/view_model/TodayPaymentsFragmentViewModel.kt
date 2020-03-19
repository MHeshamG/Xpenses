package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.DateTimeProvider.Companion.getTodayDate
import com.example.xpenses.DateTimeProvider.Companion.getTomorrowDate
import com.example.xpenses.Repository
import com.example.xpenses.RepositoryInterface
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.PaymentsDerivedInfo
import com.example.xpenses.room.PaymentDao

class TodayPaymentsFragmentViewModel(paymentsRepository: RepositoryInterface, application: Application) :
    BasePaymentsFragmentViewModel(paymentsRepository, application) {

    val todayPayments = paymentsRepository.fetchAllPaymentsBetweenDates(getTodayDate(), getTomorrowDate())
    private val dayDate = getTodayDate()


    fun getPaymentsInfo(): MediatorLiveData<List<PaymentsDerivedInfo>> {
        val paymentsInfoLiveData = MediatorLiveData<List<PaymentsDerivedInfo>>()
        paymentsInfoLiveData.addSource(todayPayments) { payments ->
            run {
                val paymentsInfo = mutableListOf<PaymentsDerivedInfo>()
                paymentsInfo.add(createTotalPaymentsCostDataItem(payments,dayDate,DateFormater.getDayDateFromMillis(dayDate.time)))
                paymentsInfo.add(createPaymentsDistributionDataItem(payments))
                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        return paymentsInfoLiveData
    }
}