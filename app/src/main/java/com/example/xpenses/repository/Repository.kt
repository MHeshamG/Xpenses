package com.example.xpenses.repository

import androidx.lifecycle.LiveData
import com.example.xpenses.model.DayBudget
import com.example.xpenses.model.Payment
import com.example.xpenses.repository.RepositoryInterface
import com.example.xpenses.room.DayBudgetDao
import com.example.xpenses.room.PaymentDao
import kotlinx.coroutines.*
import java.util.*

class Repository(private val paymentDao:PaymentDao,private val dayBudgetDao:DayBudgetDao):
    RepositoryInterface {

    private val repositoryJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+repositoryJob)

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

    override fun getDayBudget(dayDate: Date): LiveData<DayBudget> {
        return  dayBudgetDao.fetchDayBudgetByDate(dayDate)
    }

    override fun addDayBudget(dayBudget: DayBudget){
        uiScope.launch {
            _addDayBudget(dayBudget)
        }
    }

    private suspend fun _addDayBudget(dayBudget: DayBudget) {
        withContext(Dispatchers.IO){
            dayBudgetDao.addDayBudget(dayBudget)
        }
    }

    override fun updateDayBudget(dayBudget: DayBudget){
        uiScope.launch {
            _updateDayBudget(dayBudget)
        }
    }

    private suspend fun _updateDayBudget(dayBudget: DayBudget) {
        withContext(Dispatchers.IO){
            dayBudgetDao.updateDayBudget(dayBudget)
        }
    }
}