package com.xpenses.room


import androidx.lifecycle.LiveData
import androidx.room.*
import com.xpenses.model.LeafPayment

@Dao
interface PaymentDao {

    @Insert
    fun insert(payment: LeafPayment)

    @Update
    fun update(payment: LeafPayment)

    @Delete
    fun delete(payment: LeafPayment)

    @Query("DELETE FROM payments_table")
    fun deleteAllPayments()

    @Query("SELECT * FROM payments_table ORDER BY cost DESC")
    fun getAllPayments(): LiveData<List<LeafPayment>>

    @Query("SELECT * FROM payments_table WHERE paymentId=:paymentId")
    fun getPaymentById(paymentId:Long): LiveData<LeafPayment>

}