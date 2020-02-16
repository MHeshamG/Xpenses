package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xpenses.room.PaymentDao

class EditPaymentFragmentViewModelFactory(private val dataSource: PaymentDao,private val paymentId:Long, private val application: Application):
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditPaymentFragmentViewModel::class.java)) {
            return EditPaymentFragmentViewModel(dataSource, paymentId, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}