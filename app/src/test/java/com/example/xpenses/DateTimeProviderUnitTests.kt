package com.example.xpenses

import com.example.xpenses.datetime.DateTimeProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateTimeProviderUnitTests {

    @Test
    fun getTodayDateShallReturnTodayDate(){
        val returnedCurrentDate = DateTimeProvider.getTodayDate()
        val calendar = Calendar.getInstance()
        setDateParamsHourAndLess(calendar)
        val expectedCurrentDate = calendar.time
        assertEquals(returnedCurrentDate,expectedCurrentDate)
    }

    @Test
    fun getTomorrowDateShallReturnTomorrowDate(){
        val returnedTomorrowDate = DateTimeProvider.getTomorrowDate()
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        setDateParamsHourAndLess(calendar)
        val expectedTomorrowDate = calendar.time
        assertEquals(returnedTomorrowDate,expectedTomorrowDate)
    }

    @Test
    fun getThisMonthStartDateShallReturnThisMonthStartDate(){
        val returnedThisMonthStartDate = DateTimeProvider.getThisMonthStartDate()
        val calendar = Calendar.getInstance()
        setDateParamsDayAndLess(calendar)
        val expectedThisMonthStartDate = calendar.time
        assertEquals(returnedThisMonthStartDate,expectedThisMonthStartDate)
    }

    @Test
    fun getDateFromDateStringShallReturnDate(){
        val returnedDate = DateTimeProvider.getDateFromDateString("30 MAY 2020")
        val calendar = Calendar.getInstance()
        setDateParamsHourAndLess(calendar)
        val expectedCurrentDate = calendar.time
        assertEquals(returnedDate,expectedCurrentDate)
    }


    private fun setDateParamsDayAndLess(calendar: Calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        setDateParamsHourAndLess(calendar)
    }

    private fun setDateParamsHourAndLess(calendar: Calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }
}