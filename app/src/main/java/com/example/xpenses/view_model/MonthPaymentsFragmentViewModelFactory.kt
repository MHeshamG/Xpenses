package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xpenses.room.PaymentDao

class MonthPaymentsFragmentViewModelFactory(
    private val dataSource: PaymentDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MonthPaymentsFragmentViewModel::class.java)) {
            return MonthPaymentsFragmentViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}