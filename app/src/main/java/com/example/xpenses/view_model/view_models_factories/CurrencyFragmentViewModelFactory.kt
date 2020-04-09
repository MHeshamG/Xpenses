package com.example.xpenses.view_model.view_models_factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xpenses.CurrencyRepository
import com.example.xpenses.RepositoryInterface
import com.example.xpenses.view_model.CurrencyFragmentViewModel
import com.example.xpenses.view_model.EditPaymentFragmentViewModel

class CurrencyFragmentViewModelFactory(private val dataSource: CurrencyRepository): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyFragmentViewModel::class.java)) {
            return CurrencyFragmentViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}