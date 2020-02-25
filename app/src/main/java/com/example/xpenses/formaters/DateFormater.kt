package com.example.xpenses.formaters

import java.text.SimpleDateFormat

class DateFormater {
    companion object {
        fun getDateFromMillis(dateTimeMillis:Long):String{
            return SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(dateTimeMillis).toString()
        }

    }
}