package com.example.xpenses.view_model.view_models_factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xpenses.repository.CurrencyRepository
import com.example.xpenses.view_model.CurrencyFragmentViewModel

class CurrencyFragmentViewModelFactory(private val dataSource: CurrencyRepository): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyFragmentViewModel::class.java)) {
            return CurrencyFragmentViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}