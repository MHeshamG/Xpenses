package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.datetime.DateTimeProvider
import com.example.xpenses.repository.RepositoryInterface
import com.example.xpenses.model.Payment
import com.example.xpenses.model.PaymentsDerivedInfo
import java.util.*

class SpecificDayPaymentsFragmentViewModel (paymentsRepository: RepositoryInterface, application: Application) :
    BasePaymentsFragmentViewModel(paymentsRepository, application) {

    lateinit var specificDayPayments: LiveData<List<Payment>>
    lateinit var specificDayPaymentsInfo: MediatorLiveData<List<PaymentsDerivedInfo>>
    private lateinit var dayDate: Date


    fun fetchSpecificDayPayments(dayDateString:String) {
        val dayDate = DateTimeProvider.getDateFromDateString(dayDateString)
        this.dayDate = dayDate
        val nextDayDate = DateTimeProvider.getDateOfNextDay(dayDate)
        specificDayPayments = paymentsRepository.fetchAllPaymentsBetweenDates(dayDate, nextDayDate)
        specificDayPaymentsInfo = getPaymentsInfo(specificDayPayments)
    }

    private fun getPaymentsInfo(src: LiveData<List<Payment>>): MediatorLiveData<List<PaymentsDerivedInfo>> {
        val paymentsInfoLiveData = MediatorLiveData<List<PaymentsDerivedInfo>>()
        paymentsInfoLiveData.addSource(src) { payments ->
            run {
                val paymentsInfo = mutableListOf<PaymentsDerivedInfo>()
                paymentsInfo.add(createTotalPaymentsCostDataItem(payments,dayDate))
                paymentsInfo.add(createPaymentsDistributionDataItem(payments))
                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        return paymentsInfoLiveData
    }
}