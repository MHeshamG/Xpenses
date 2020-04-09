package com.example.xpenses.model

import com.squareup.moshi.Json

data class CurrencyResponse(val rates:Rates)
data class Rates(@Json(name="EGP")val EGP:Double, @Json(name="EUR")val EUR:Double)
