package com.example.xpenses.network

import com.example.xpenses.model.CurrencyResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://metals-api.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CurrencyService {
    @GET("latest?")
    suspend fun getCurrenciesRate(@Query("access_key") key:String):CurrencyResponse
}

object CurrencyApi {
    val retrofitService : CurrencyService by lazy {
        retrofit.create(CurrencyService::class.java) }
}
