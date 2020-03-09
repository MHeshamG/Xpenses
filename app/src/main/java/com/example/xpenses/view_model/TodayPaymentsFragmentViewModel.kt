package com.example.xpenses.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.DateTimeProvider
import com.example.xpenses.DateTimeProvider.Companion.getTodayDate
import com.example.xpenses.DateTimeProvider.Companion.getTomorrowDate
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.Payment
import com.example.xpenses.model.PaymentsDerivedInfo
import com.example.xpenses.room.PaymentDao

class TodayPaymentsFragmentViewModel( paymentDao: PaymentDao, application: Application) :
    BasePaymentsFragmentViewModel(paymentDao, application) {

    val todayPayments = paymentDao.getAllPaymentsBetweenDates(getTodayDate(), getTomorrowDate())
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