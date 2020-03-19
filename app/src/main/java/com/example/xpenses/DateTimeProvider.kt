package com.example.xpenses

import java.text.SimpleDateFormat
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
            return calendar.time
        }

        fun getNextMonthStartDate(): Date {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, 1)
            setDateParamsDaysAndLess(calendar)
            return calendar.time
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

        fun getThisMonthDate(): Date {
            val calendar = Calendar.getInstance()
            setDateParamsDaysAndLess(calendar)
            return calendar.time
        }

        fun getDateFromDateString(dayDateString: String/*dayDateString format d mmm yyyy*/): Date {
            val calendar = Calendar.getInstance()
            setDateParamsHoursAndLess(calendar)
            val listOfDateAttr = dayDateString.split(" ")
            val monthIntValue = getMonthIntValueFromMonthStringValue(listOfDateAttr[1])
            calendar.set(Calendar.DAY_OF_MONTH,listOfDateAttr[0].toInt())
            calendar.set(Calendar.MONTH,monthIntValue.get(Calendar.MONTH))
            calendar.set(Calendar.YEAR,listOfDateAttr[2].toInt())
            return calendar.time
        }

        fun getDateOfNextDay(dayDate: Date): Date {
            val calendar = Calendar.getInstance()
            calendar.time = dayDate
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            setDateParamsHoursAndLess(calendar)
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

        private fun getMonthIntValueFromMonthStringValue(monthString:String): Calendar {
            val month: Date = SimpleDateFormat("MMM", Locale.ENGLISH).parse(monthString)
            val cal = Calendar.getInstance()
            cal.time = month
            return cal
        }
    }
}