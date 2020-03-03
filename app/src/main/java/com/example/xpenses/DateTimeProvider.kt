package com.example.xpenses

import android.util.Log
import java.util.*

class DateTimeProvider {

    companion object {
        fun getTodayDate(): Date {
            val calendar = Calendar.getInstance()
            setDateParamsHoursAndLess(calendar)
            return calendar.time
        }

        fun getTomorrowDate(): Date {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            setDateParamsHoursAndLess(calendar)
            return calendar.time
        }

        fun getThisMonthStartDate(): Date {
            val calendar = Calendar.getInstance()
            setDateParamsDaysAndLess(calendar)
            Log.d("xxx",calendar.get(Calendar.MONTH).toString()+" "+calendar.time)
            return calendar.time
        }

        fun getNextMonthStartDate(): Date {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, 1)
            setDateParamsDaysAndLess(calendar)
            Log.d("xxx",calendar.get(Calendar.MONTH).toString()+" "+calendar.time)
            return calendar.time
        }

        private fun setDateParamsHoursAndLess(calendar: Calendar) {
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
        }

        private fun setDateParamsDaysAndLess(calendar: Calendar) {
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            setDateParamsHoursAndLess(calendar)
        }

        fun getDateFromDateTime(dateTime:Date):Date{
            val calendar = Calendar.getInstance()
            calendar.time = dateTime
            setDateParamsHoursAndLess(calendar)
            return calendar.time
        }
        fun getDayOfDateFromDateTime(dateTime:Date):Int{
            val calendar = Calendar.getInstance()
            calendar.time = dateTime
            return calendar.get(Calendar.DAY_OF_MONTH)
        }
    }
}