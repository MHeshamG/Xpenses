package com.example.xpenses.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.xpenses.model.DayBudget
import com.example.xpenses.model.Payment
import java.util.*

interface RepositoryInterface {

    fun savePayment(payment: Payment)

    fun updatePayment(payment: Payment)

    fun deletePayment(payment: Payment)

    fun deleteAllPayments()

    fun fetchAllPayments(): LiveData<List<Payment>>

    fun fetchAllPaymentsBetweenDates(from: Date, to: Date): LiveData<List<Payment>>

    fun fetchPaymentById(paymentId:Long): LiveData<Payment>

    fun getDayBudget(dayDate:Date): LiveData<DayBudget>

    fun addDayBudget(dayBudget: DayBudget)

    fun updateDayBudget(dayBudget: DayBudget)
}