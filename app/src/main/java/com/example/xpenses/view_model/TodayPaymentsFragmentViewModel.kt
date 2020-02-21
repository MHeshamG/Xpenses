package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.view.recycler_view.CarouselAdapter
import com.xpenses.room.PaymentDao
import com.xwallet.business.PaymentType
import java.util.*

class TodayPaymentsFragmentViewModel(val paymentDao: PaymentDao, application: Application) :
    AndroidViewModel(application) {
    val todayPayments = paymentDao.getAllPaymentsBetweenDates(getTodayDate(), getTomorrowDate())
    fun totalCost(): MediatorLiveData<List<CarouselAdapter.DataItem>> {
        val paymentsInfoLiveData = MediatorLiveData<List<CarouselAdapter.DataItem>>()
        paymentsInfoLiveData.addSource(
            todayPayments
        ) { payments ->
            run {
                val paymentsInfo = mutableListOf<CarouselAdapter.DataItem>()
                paymentsInfo.add( CarouselAdapter.DataItem.PaymentsCost(payments.sumByDouble { it.cost }))
                val mapOfPaymentTypeAgainstCost = mutableMapOf<PaymentType, Double>()
                payments.iterator().forEach {
                        val currentCost = mapOfPaymentTypeAgainstCost.get(PaymentType.fromInt(it.type))?:0.0
                        mapOfPaymentTypeAgainstCost[PaymentType.fromInt(it.type)!!] = it.cost + currentCost
                }
                paymentsInfo.add(CarouselAdapter.DataItem.PaymentsDistrbution(mapOfPaymentTypeAgainstCost))
                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        return paymentsInfoLiveData
    }



    fun getTodayDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun getTomorrowDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }
}