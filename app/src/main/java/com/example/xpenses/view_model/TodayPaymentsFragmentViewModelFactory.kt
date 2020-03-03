package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xpenses.room.PaymentDao

class TodayPaymentsFragmentViewModelFactory(
    private val dataSource: PaymentDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodayPaymentsFragmentViewModel::class.java)) {
            return TodayPaymentsFragmentViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}