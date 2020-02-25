package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.xpenses.model.LeafPayment
import com.xpenses.room.PaymentDao
import kotlinx.coroutines.*

class AddPaymentFragmentViewModel(val paymentDao: PaymentDao, application: Application): AndroidViewModel(application)  {

    private val AddPaymentFragmentViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+AddPaymentFragmentViewModelJob)

    fun onSavePayment(payment: LeafPayment){
        uiScope.launch {
            savePayment(payment)
        }
    }

    private suspend fun savePayment(payment: LeafPayment) {
        withContext(Dispatchers.IO){
            paymentDao.insert(payment)
        }
    }

}