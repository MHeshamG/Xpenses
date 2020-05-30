package com.example.xpenses.repository

import com.example.xpenses.model.CurrencyResponse
import com.example.xpenses.network.CurrencyApi

class CurrencyRepository {
    private val key = "uf3ay3onb2wl4b3jt5zyumec3ptf62u93a1zb8o6s0331n1p3yfg4c77a7b6"

    suspend fun fetchCurrencies():CurrencyResponse{
        return CurrencyApi.retrofitService.getCurrenciesRate(key)
    }
}