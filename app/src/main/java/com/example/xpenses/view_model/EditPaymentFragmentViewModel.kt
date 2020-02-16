package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.xpenses.model.LeafPayment
import com.xpenses.room.PaymentDao
import kotlinx.coroutines.*

class EditPaymentFragmentViewModel(val paymentDao: PaymentDao, val paymentId:Long, application: Application) :
    AndroidViewModel(application) {

    val thisPayment = paymentDao.getPaymentById(paymentId)

    private val EditPaymentFragmentViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + EditPaymentFragmentViewModelJob)

    fun onUpdatePayment(payment: LeafPayment) {
        uiScope.launch {
            updatePayment(payment)
        }
    }

    private suspend fun updatePayment(payment: LeafPayment) {
        withContext(Dispatchers.IO) {
            paymentDao.update(payment)
        }
    }

    fun onDeletePayment(leafPayment: LeafPayment) {
        uiScope.launch {
            deletePayment(leafPayment)
        }
    }

    private suspend fun deletePayment(leafPayment: LeafPayment) {
        withContext(Dispatchers.IO) {
            paymentDao.delete(leafPayment)
        }
    }

}