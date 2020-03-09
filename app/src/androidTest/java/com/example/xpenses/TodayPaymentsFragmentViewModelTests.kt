package com.example.xpenses

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.xpenses.DateTimeProvider.Companion.getTodayDate
import com.example.xpenses.DateTimeProvider.Companion.getTomorrowDate
import com.example.xpenses.model.PaymentsDerivedInfo
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModel
import com.xpenses.model.PaymentType
import com.example.xpenses.room.PaymentDao
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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
        } returns MutableLiveData()
        val application = mockk<Application>()
        todayPaymentsFragmentViewModel = TodayPaymentsFragmentViewModel(paymentDao, application)
    }

    @After
    fun end(){
       // MockKAnnotations.
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
        ) as PaymentsDerivedInfo.PaymentsTotalCostOfDate
        assertEquals(paymentsTotalCost.totalCost, expectedTotalCostOfPayments,0.0)
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
        val paymentsDistribution = createPaymentsDistributionDataItemMethod.invoke(todayPaymentsFragmentViewModel,listOfPayments) as PaymentsDerivedInfo.PaymentsCostDistributionAgainstType
        val actualMap = paymentsDistribution.mapOfPaymentTypeAgainstCost
        assertEquals(actualMap,expectedMap)
    }
}