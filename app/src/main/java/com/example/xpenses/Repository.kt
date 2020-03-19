package com.example.xpenses

import androidx.lifecycle.LiveData
import com.example.xpenses.model.Payment
import com.example.xpenses.room.PaymentDao
import java.util.*

class Repository(private val paymentDao:PaymentDao):RepositoryInterface {
    override fun savePayment(payment: Payment) {
        paymentDao.savePayment(payment)
    }

    override fun updatePayment(payment: Payment) {
        paymentDao.updatePayment(payment)
    }

    override fun deletePayment(payment: Payment) {
        paymentDao.delete(payment)
    }

    override fun deleteAllPayments() {
        paymentDao.deleteAllPayments()
    }

    override fun fetchAllPayments(): LiveData<List<Payment>> {
        return paymentDao.fetchAllPayments()
    }

    override fun fetchAllPaymentsBetweenDates(from: Date, to: Date): LiveData<List<Payment>> {
        return paymentDao.fetchAllPaymentsBetweenDates(from, to)
    }

    override fun fetchPaymentById(paymentId: Long): LiveData<Payment> {
        return paymentDao.fetchPaymentById(paymentId)
    }
}