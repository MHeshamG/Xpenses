package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.xpenses.Repository
import com.example.xpenses.RepositoryInterface
import com.example.xpenses.model.Payment
import com.example.xpenses.room.PaymentDao
import kotlinx.coroutines.*

class AddPaymentFragmentViewModel(val paymentsRepository: RepositoryInterface, application: Application): AndroidViewModel(application)  {

    private val AddPaymentFragmentViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+AddPaymentFragmentViewModelJob)

    fun onSavePayment(payment: Payment){
        uiScope.launch {
            savePayment(payment)
        }
    }

    private suspend fun savePayment(payment: Payment) {
        withContext(Dispatchers.IO){
            paymentsRepository.savePayment(payment)
        }
    }

}