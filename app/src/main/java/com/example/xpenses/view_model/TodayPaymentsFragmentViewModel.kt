package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.xpenses.room.PaymentDao
import java.util.*

class TodayPaymentsFragmentViewModel(val paymentDao: PaymentDao, application: Application):AndroidViewModel(application) {
    val todayPayments = paymentDao.getAllPaymentsBetweenDates(getTodayDate(),getTomorrowDate())
//    fun totalCost():MediatorLiveData<Double>{
//        val totalCost:MediatorLiveData<Double> = MediatorLiveData()
//        totalCost.addSource(todayPayments
//        ) { payments -> totalCost.value = payments.sumByDouble { it.cost } }
//        return totalCost
//    }

    fun getTodayDate():Date{
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun getTomorrowDate():Date{
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH,1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }
}