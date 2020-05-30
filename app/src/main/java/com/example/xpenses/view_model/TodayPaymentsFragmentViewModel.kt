package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.xpenses.datetime.DateTimeProvider.Companion.getTodayDate
import com.example.xpenses.datetime.DateTimeProvider.Companion.getTomorrowDate
import com.example.xpenses.repository.RepositoryInterface
import com.example.xpenses.model.DayBudget
import com.example.xpenses.model.Payment
import com.example.xpenses.model.PaymentsDerivedInfo

class TodayPaymentsFragmentViewModel(paymentsRepository: RepositoryInterface, application: Application) :
    BasePaymentsFragmentViewModel(paymentsRepository, application) {

    private val dayDate = getTodayDate()

    val todayPayments = paymentsRepository.fetchAllPaymentsBetweenDates(dayDate, getTomorrowDate())
    val todayBudget = paymentsRepository.getDayBudget(dayDate)
    val todayPaymentsInfo = getPaymentsInfo(todayPayments,todayBudget)

    fun addTodayBudget(budget:Double){
        if(todayBudget.value == null)
            paymentsRepository.addDayBudget(DayBudget(budget,dayDate))
        else {
            todayBudget.value?.budget = budget
            paymentsRepository.updateDayBudget(todayBudget.value!!)
        }
    }

    private fun getPaymentsInfo(src: LiveData<List<Payment>>,src2:LiveData<DayBudget>): MediatorLiveData<List<PaymentsDerivedInfo>> {
        val paymentsInfoLiveData = MediatorLiveData<List<PaymentsDerivedInfo>>()
        val paymentsInfo = mutableListOf<PaymentsDerivedInfo>()

        lateinit var todayTotalPaymentsCostAndBudget:PaymentsDerivedInfo.PaymentsTotalCostAndBudgetOfDate
        lateinit var todayPaymentsDistribution:PaymentsDerivedInfo.PaymentsCostDistributionAgainstType

        paymentsInfoLiveData.addSource(src) { payments ->
            run {
                todayTotalPaymentsCostAndBudget = createTotalPaymentsCostDataItem(payments,dayDate)
                todayPaymentsDistribution = createPaymentsDistributionDataItem(payments)

                paymentsInfo.add(todayTotalPaymentsCostAndBudget)
                paymentsInfo.add(todayPaymentsDistribution)

                paymentsInfoLiveData.value = paymentsInfo
            }
        }
        paymentsInfoLiveData.addSource(src2){
            todayTotalPaymentsCostAndBudget?.dayBudget = it?.budget

            paymentsInfoLiveData.value = paymentsInfo
        }
        return paymentsInfoLiveData
    }
}