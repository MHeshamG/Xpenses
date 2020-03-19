package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.xpenses.Repository
import com.example.xpenses.RepositoryInterface
import com.example.xpenses.model.Payment
import com.example.xpenses.room.PaymentDao
import kotlinx.coroutines.*

class EditPaymentFragmentViewModel(private val paymentsRepository: RepositoryInterface, val paymentId:Long, application: Application) :
    AndroidViewModel(application) {

    val thisPayment = paymentsRepository.fetchPaymentById(paymentId)

    private val EditPaymentFragmentViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + EditPaymentFragmentViewModelJob)

    fun onUpdatePayment(payment: Payment) {
        uiScope.launch {
            updatePayment(payment)
        }
    }

    private suspend fun updatePayment(payment: Payment) {
        withContext(Dispatchers.IO) {
            paymentsRepository.updatePayment(payment)
        }
    }

    fun onDeletePayment() {
        uiScope.launch {
            deletePayment(thisPayment.value!!)
        }
    }

    private suspend fun deletePayment(payment: Payment) {
        withContext(Dispatchers.IO) {
            paymentsRepository.deletePayment(payment)
        }
    }

}