package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.xpenses.model.Payment
import com.example.xpenses.room.PaymentDao
import kotlinx.coroutines.*

class AddPaymentFragmentViewModel(val paymentDao: PaymentDao, application: Application): AndroidViewModel(application)  {

    private val AddPaymentFragmentViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+AddPaymentFragmentViewModelJob)

    fun onSavePayment(payment: Payment){
        uiScope.launch {
            savePayment(payment)
        }
    }

    private suspend fun savePayment(payment: Payment) {
        withContext(Dispatchers.IO){
            paymentDao.insert(payment)
        }
    }

}