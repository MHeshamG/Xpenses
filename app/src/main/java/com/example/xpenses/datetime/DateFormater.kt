package com.example.xpenses.datetime

import java.text.SimpleDateFormat

class DateFormater {
    companion object {
        fun getFullDateTimeFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(dateTimeMillis).toString()
        }

        fun getDayDateFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("EEE, d MMM yyyy").format(dateTimeMillis).toString()
        }

        fun getDayDateWithoutDayShortNameFormatFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("d MMM yyyy").format(dateTimeMillis).toString()
        }

        fun getMonthDateFormatFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("MMM yyyy").format(dateTimeMillis).toString()
        }

    }
}