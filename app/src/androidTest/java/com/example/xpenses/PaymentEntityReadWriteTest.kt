package com.example.xpenses

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.xpenses.room.PaymentDao
import com.xpenses.room.PaymentsDatabase
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@RunWith(AndroidJUnit4::class)
class PaymentEntityReadWriteTest {
    private lateinit var paymentDao: PaymentDao
    private lateinit var db: PaymentsDatabase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PaymentsDatabase::class.java
        ).allowMainThreadQueries().build()
        paymentDao = db.paymentDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writePaymentAndReadAll() {
        val payment = FakePaymentsDataSource.createPayment()
        paymentDao.insert(payment)
        val payments = paymentDao.getAllPayments()
        assertThat(payments.getOrAwaitValue().get(0).cost, equalTo(payment.cost))
    }

    @Test
    @Throws(Exception::class)
    fun writePaymentAndReadWithId() {
        val payment = FakePaymentsDataSource.createPayment()
        paymentDao.insert(payment)
        val paymentReturned = paymentDao.getPaymentById(1L)
        assertThat(paymentReturned.getOrAwaitValue().cost, equalTo(payment.cost))
    }

    @Test
    @Throws(Exception::class)
    fun writePaymentAndDeleteWithId() {
        val payment = FakePaymentsDataSource.createPayment()
        paymentDao.insert(payment)
        paymentDao.delete(payment)
        val paymentReturned = paymentDao.getPaymentById(0)
        assertNull(paymentReturned.getOrAwaitValue())
    }

    @Test
    @Throws(Exception::class)
    fun writePaymentAndDeleteAll() {
        val payment = FakePaymentsDataSource.createPayment()
        paymentDao.insert(payment)
        paymentDao.deleteAllPayments()
        val payments = paymentDao.getAllPayments()
        assertEquals(payments.getOrAwaitValue().size, 0)
    }

    @Test
    @Throws(Exception::class)
    fun getPaymentsWithDay() {
        var todayDate = getDateFromLocalDate(LocalDate.now())
        var dateOfTomorrow = getDateFromLocalDate(LocalDate.now().plusDays(1))
        val payment = FakePaymentsDataSource.createPayment()
        val payment2 = FakePaymentsDataSource.createPaymentWithCustomDate(dateOfTomorrow)
        paymentDao.insert(payment)
        paymentDao.insert(payment2)
        val payments = paymentDao.getAllPaymentsBetweenDates(todayDate,dateOfTomorrow)
        assertEquals(payments.getOrAwaitValue().size, 1)
    }

    private fun getDateFromLocalDate(localDate: LocalDate) =
        Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())

}

