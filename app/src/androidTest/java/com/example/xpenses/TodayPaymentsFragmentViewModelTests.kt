package com.example.xpenses

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.xpenses.ui_data_models.DataItem
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModel
import com.xpenses.model.LeafPayment
import com.xpenses.model.PaymentType
import com.xpenses.room.PaymentDao
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class TodayPaymentsFragmentViewModelTests {

    private lateinit var todayPaymentsFragmentViewModel: TodayPaymentsFragmentViewModel


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val paymentDao = mockk<PaymentDao>()
        every {
            paymentDao.getAllPaymentsBetweenDates(
                getTodayDate(),
                getTomorrowDate()
            )
        } returns MutableLiveData<List<LeafPayment>>()
        val application = mockk<Application>()
        todayPaymentsFragmentViewModel = TodayPaymentsFragmentViewModel(paymentDao, application)
    }

    @Test
    fun createTotalPaymentsCostDataItemShouldReturnDataItemWithTotalCost() {
        val createTotalPaymentsCostDataItemMethod =
            todayPaymentsFragmentViewModel.javaClass.getDeclaredMethod(
                "createTotalPaymentsCostDataItem",
                List::class.java
            )
        createTotalPaymentsCostDataItemMethod.isAccessible = true
        val listOfPayments = FakePaymentsDataSource.createPayments()
        val expectedTotalCostOfPayments = listOfPayments.sumByDouble { it.cost }
        val paymentsTotalCost = createTotalPaymentsCostDataItemMethod.invoke(
            todayPaymentsFragmentViewModel,
            listOfPayments
        ) as DataItem.PaymentsCost
        assertEquals(paymentsTotalCost.totalCost, expectedTotalCostOfPayments)
    }

    @Test
    fun createPaymentsDistributionDataItemShouldReturnMapOfPaymentsTypeAgainstCost() {
        val createPaymentsDistributionDataItemMethod =
            todayPaymentsFragmentViewModel.javaClass.getDeclaredMethod(
                "createPaymentsDistributionDataItem",
                List::class.java
            )
        createPaymentsDistributionDataItemMethod.isAccessible = true
        val listOfPayments = FakePaymentsDataSource.createPayments()
        val expectedMap = mapOf(PaymentType.SHOPPING to 300.0,PaymentType.FOOD to 102.0,PaymentType.ENTERTAINMENT to 200.0)
        val paymentsDistribution = createPaymentsDistributionDataItemMethod.invoke(todayPaymentsFragmentViewModel,listOfPayments) as DataItem.PaymentsDistribution
        val actualMap = paymentsDistribution.mapOfPaymentTypeAgainstCost
        assertEquals(actualMap,expectedMap)
    }

    private fun getTodayDate(): Date {
        val calendar = Calendar.getInstance()
        setDateParams(calendar)
        return calendar.time
    }

    private fun getTomorrowDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        setDateParams(calendar)
        return calendar.time
    }

    private fun setDateParams(calendar: Calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }
}