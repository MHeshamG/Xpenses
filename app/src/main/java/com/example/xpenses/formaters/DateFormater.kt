package com.example.xpenses.formaters

import java.text.SimpleDateFormat

class DateFormater {
    companion object {
        fun getFullDateTimeFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(dateTimeMillis).toString()
        }

        fun getDayDateFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("EEE, d MMM yyyy").format(dateTimeMillis).toString()
        }

        fun getDayDateSecondFormatFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("dd MM yyyy").format(dateTimeMillis).toString()
        }

        fun getMonthFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("MMM yyyy").format(dateTimeMillis).toString()
        }

    }
}