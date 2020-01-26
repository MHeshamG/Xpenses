package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.xwallet.business.LeafPayment
import com.xwallet.room.PaymentDao
import kotlinx.coroutines.*

class TodayPaymentsFragmentViewModel(val paymentDao:PaymentDao,application: Application):AndroidViewModel(application) {

    private val todayPaymentsViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+todayPaymentsViewModelJob)
    val todayPayments = paymentDao.getAllPayments()

    fun onSavePayment(payment:LeafPayment){
        uiScope.launch {
           savePayment(payment)
        }
    }

    private suspend fun savePayment(payment: LeafPayment) {
        withContext(Dispatchers.IO){
            paymentDao.insert(payment)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}