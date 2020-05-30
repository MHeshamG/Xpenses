package com.example.xpenses.view_model.view_models_factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xpenses.repository.RepositoryInterface
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModel

class TodayPaymentsFragmentViewModelFactory(private val dataSource: RepositoryInterface, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodayPaymentsFragmentViewModel::class.java))
            return TodayPaymentsFragmentViewModel(dataSource, application) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}