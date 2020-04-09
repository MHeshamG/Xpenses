package com.example.xpenses.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.DateTimeProvider.Companion.getTodayDate
import com.example.xpenses.DateTimeProvider.Companion.getTomorrowDate
import com.example.xpenses.RepositoryInterface
import com.example.xpenses.formaters.DateFormater
import com.example.xpenses.model.DayBudget
import com.example.xpenses.model.Payment
import com.example.xpenses.model.PaymentsDerivedInfo

class TodayPaymentsFragmentViewModel(paymentsRepository: RepositoryInterface, application: Application) :
    BasePaymentsFragmentViewModel(paymentsRepository, application) {

    private var arePaymentsRetrieved:Boolean = false
    private var isBudgetRetrieved:Boolean = false

    private val dayDate = getTodayDate()

    val todayPayments = paymentsRepository.fetchAllPaymentsBetweenDates(dayDate, getTomorrowDate())
    val todayBudget = paymentsRepository.getDayBudget(dayDate)
    val todayPaymentsInfo = getPaymentsInfo(todayPayments,todayBudget)

    var todayTotalPaymentsCost:PaymentsDerivedInfo.PaymentsTotalCostOfDate = PaymentsDerivedInfo.PaymentsTotalCostOfDate(dayDate)

    fun addTodayBudget(budget:Double){
        if(todayBudget.value == null)
            paymentsRepository.addDayBudget(DayBudget(budget,dayDate))
        else {
            val dayBudget = todayBudget.value
            dayBudget?.budget = budget
            paymentsRepository.updateDayBudget(dayBudget!!)
        }
    }

    private fun getPaymentsInfo(src: LiveData<List<Payment>>,src2:LiveData<DayBudget>): MediatorLiveData<List<PaymentsDerivedInfo>> {
        val paymentsInfoLiveData = MediatorLiveData<List<PaymentsDerivedInfo>>()
        val paymentsInfo = mutableListOf<PaymentsDerivedInfo>()

        var todayPaymentsDistribution:PaymentsDerivedInfo.PaymentsCostDistributionAgainstType? = null
        paymentsInfoLiveData.addSource(src) { payments ->
            run {
                todayTotalPaymentsCost.totalCost = payments.sumByDouble { it.cost }
                todayTotalPaymentsCost.dateString = DateFormater.getDayDateFromMillis(dayDate.time)
                //createTotalPaymentsCostDataItem(payments,dayDate, DateFormater.getDayDateFromMillis(dayDate.time))
                todayPaymentsDistribution = createPaymentsDistributionDataItem(payments)
                paymentsInfo.add(todayTotalPaymentsCost)
                paymentsInfo.add(todayPaymentsDistribution!!)
                arePaymentsRetrieved = true
                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        paymentsInfoLiveData.addSource(src2){
            todayTotalPaymentsCost.dayBudget = it?.budget
            isBudgetRetrieved = true
            paymentsInfoLiveData.value = paymentsInfo
        }
       // if(arePaymentsRetrieved && isBudgetRetrieved)

        Log.d("xxx","$arePaymentsRetrieved  $isBudgetRetrieved")
        return paymentsInfoLiveData
    }
}