package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.xpenses.model.Payment
import com.example.xpenses.room.PaymentDao
import kotlinx.coroutines.*

class EditPaymentFragmentViewModel(val paymentDao: PaymentDao, val paymentId:Long, application: Application) :
    AndroidViewModel(application) {

    val thisPayment = paymentDao.getPaymentById(paymentId)

    private val EditPaymentFragmentViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + EditPaymentFragmentViewModelJob)

    fun onUpdatePayment(payment: Payment) {
        uiScope.launch {
            updatePayment(payment)
        }
    }

    private suspend fun updatePayment(payment: Payment) {
        withContext(Dispatchers.IO) {
            paymentDao.update(payment)
        }
    }

    fun onDeletePayment() {
        uiScope.launch {
            deletePayment(thisPayment.value!!)
        }
    }

    private suspend fun deletePayment(payment: Payment) {
        withContext(Dispatchers.IO) {
            paymentDao.delete(payment)
        }
    }

}