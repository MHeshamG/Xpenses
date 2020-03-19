package com.example.xpenses.room


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.xpenses.model.Payment
import java.util.*

@Dao
interface PaymentDao {

    @Insert
    fun savePayment(payment: Payment)

    @Update
    fun updatePayment(payment: Payment)

    @Delete
    fun delete(payment: Payment)

    @Query("DELETE FROM payments_table")
    fun deleteAllPayments()

    @Query("SELECT * FROM payments_table ORDER BY cost DESC")
    fun fetchAllPayments(): LiveData<List<Payment>>

    @Query("SELECT * FROM payments_table WHERE dateTime >= :from AND dateTime < :to ORDER BY cost DESC")
    fun fetchAllPaymentsBetweenDates(from: Date, to: Date): LiveData<List<Payment>>

    @Query("SELECT * FROM payments_table WHERE paymentId=:paymentId")
    fun fetchPaymentById(paymentId:Long): LiveData<Payment>

}