package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.xpenses.room.PaymentDao
import com.xwallet.business.LeafPayment
import kotlinx.coroutines.*

class AddPaymentFragmentViewModel(val paymentDao: PaymentDao, application: Application): AndroidViewModel(application)  {

    private val AddEditPaymentFragmentViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+AddEditPaymentFragmentViewModelJob)

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