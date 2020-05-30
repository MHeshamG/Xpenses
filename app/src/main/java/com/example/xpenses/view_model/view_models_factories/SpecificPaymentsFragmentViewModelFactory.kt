package com.example.xpenses.view_model.view_models_factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xpenses.repository.RepositoryInterface
import com.example.xpenses.view_model.SpecificDayPaymentsFragmentViewModel

class SpecificPaymentsFragmentViewModelFactory(private val dataSource: RepositoryInterface, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpecificDayPaymentsFragmentViewModel::class.java)) {
            return SpecificDayPaymentsFragmentViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}