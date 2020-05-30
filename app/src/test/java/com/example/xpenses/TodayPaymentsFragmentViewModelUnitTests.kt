package com.example.xpenses

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.xpenses.datetime.DateTimeProvider
import com.example.xpenses.datetime.DateTimeProvider.Companion.getTodayDate
import com.example.xpenses.model.DayBudget
import com.example.xpenses.repository.Repository
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class TodayPaymentsFragmentViewModelUnitTests {

    private lateinit var todayPaymentsFragmentViewModel: TodayPaymentsFragmentViewModel
    private lateinit var paymentsRepository: Repository
    val dayBudget = DayBudget(1000.0, getTodayDate())

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        paymentsRepository = mockk()
        every {
            paymentsRepository.fetchAllPaymentsBetweenDates(
                DateTimeProvider.getTodayDate(),
                DateTimeProvider.getTomorrowDate()
            )
        } returns MutableLiveData()
        every {
            paymentsRepository.getDayBudget(DateTimeProvider.getTodayDate())
        } returns MutableLiveData()
        val application = mockk<Application>()
        todayPaymentsFragmentViewModel =
            TodayPaymentsFragmentViewModel(paymentsRepository, application)
    }

    @After
    fun end() {
        // MockKAnnotations.
    }

    @Test
    fun addBudgetShouldAddBudgetWhenTheirIsNoBudgetSet() {
        todayPaymentsFragmentViewModel.addTodayBudget(1000.0)
        runBlocking {
            verify { paymentsRepository.addDayBudget(mockk()) }
        }
    }

//    @Test
//    fun createTotalPaymentsCostDataItemShouldReturnDataItemWithTotalCost() {
//        val createTotalPaymentsCostDataItemMethod =
//            todayPaymentsFragmentViewModel.javaClass.getDeclaredMethod(
//                "createTotalPaymentsCostDataItem",
//                List::class.java
//            )
//        createTotalPaymentsCostDataItemMethod.isAccessible = true
//        val listOfPayments = FakePaymentsDataSource.createPayments()
//        val expectedTotalCostOfPayments = listOfPayments.sumByDouble { it.cost }
//        val paymentsTotalCost = createTotalPaymentsCostDataItemMethod.invoke(
//            todayPaymentsFragmentViewModel,
//            listOfPayments
//        ) as PaymentsDerivedInfo.PaymentsTotalCostOfDate
//        Assert.assertEquals(paymentsTotalCost.totalCost, expectedTotalCostOfPayments, 0.0)
//    }

//    @Test
//    fun createPaymentsDistributionDataItemShouldReturnMapOfPaymentsTypeAgainstCost() {
//        val createPaymentsDistributionDataItemMethod =
//            todayPaymentsFragmentViewModel.javaClass.getDeclaredMethod(
//                "createPaymentsDistributionDataItem",
//                List::class.java
//            )
//        createPaymentsDistributionDataItemMethod.isAccessible = true
//        val listOfPayments = FakePaymentsDataSource.createPayments()
//        val expectedMap = mapOf(
//            PaymentType.SHOPPING to 300.0,
//            PaymentType.FOOD to 102.0,
//            PaymentType.ENTERTAINMENT to 200.0)
//        val paymentsDistribution = createPaymentsDistributionDataItemMethod.invoke(todayPaymentsFragmentViewModel,listOfPayments) as PaymentsDerivedInfo.PaymentsCostDistributionAgainstType
//        val actualMap = paymentsDistribution.mapOfPaymentTypeAgainstCost
//        Assert.assertEquals(actualMap, expectedMap)
//    }
}