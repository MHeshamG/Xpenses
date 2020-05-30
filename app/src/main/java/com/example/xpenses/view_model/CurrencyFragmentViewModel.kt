package com.example.xpenses.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xpenses.repository.CurrencyRepository
import com.example.xpenses.model.CurrencyRate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CurrencyFragmentViewModel(private val currencyRepository: CurrencyRepository) : ViewModel() {
    val currenciesRate: MutableLiveData<List<CurrencyRate>> = MutableLiveData()
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun fetchCurrenciesRate() {
        coroutineScope.launch {
            try {
                val currenciesRateResponse = currencyRepository.fetchCurrencies()
                val currenciesRateList = mutableListOf<CurrencyRate>()
                currenciesRateList.add(CurrencyRate("EGP",currenciesRateResponse.rates.EGP))
                currenciesRateList.add(CurrencyRate("EUR",currenciesRateResponse.rates.EUR))
                currenciesRate.value = currenciesRateList
            } catch (e: Exception) {
                Log.d("xxx","error retrieving data")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}