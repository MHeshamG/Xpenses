package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.DateTimeProvider
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.Payment
import com.example.xpenses.model.PaymentsDerivedInfo
import com.example.xpenses.room.PaymentDao
import java.util.*

class SpecificPaymentsFragmentViewModel (paymentDao: PaymentDao, application: Application) :
    BasePaymentsFragmentViewModel(paymentDao, application) {

    lateinit var specificDayPayments: LiveData<List<Payment>>
    lateinit var specificDayPaymentsInfo: MediatorLiveData<List<PaymentsDerivedInfo>>
    private lateinit var dayDate: Date


    fun fetchSpecificDayPayments(dayDateString:String) {
        val dayDate = DateTimeProvider.getDateFromDateString(dayDateString)
        this.dayDate = dayDate
        val nextDayDate = DateTimeProvider.getDateOfNextDay(dayDate)
        specificDayPayments = paymentDao.getAllPaymentsBetweenDates(dayDate, nextDayDate)
        specificDayPaymentsInfo = getPaymentsInfo(specificDayPayments)
    }

    private fun getPaymentsInfo(src: LiveData<List<Payment>>): MediatorLiveData<List<PaymentsDerivedInfo>> {
        val paymentsInfoLiveData = MediatorLiveData<List<PaymentsDerivedInfo>>()
        paymentsInfoLiveData.addSource(src) { payments ->
            run {
                val paymentsInfo = mutableListOf<PaymentsDerivedInfo>()
                paymentsInfo.add(createTotalPaymentsCostDataItem(payments,dayDate, DateFormater.getDayDateFromMillis(dayDate.time)))
                paymentsInfo.add(createPaymentsDistributionDataItem(payments))
                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        return paymentsInfoLiveData
    }
}