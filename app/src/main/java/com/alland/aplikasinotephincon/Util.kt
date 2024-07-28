package com.alland.aplikasinotephincon

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Util {
    fun convertMillisToReadableTimeWithFormat(millis: Long, format: String = "dd MMM yyyy"): String {
        val date = Date(millis)
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(date)
    }

    fun isSameYear(millis: Long): Boolean {
        val currentCalendar = Calendar.getInstance()
        val givenCalendar = Calendar.getInstance()
        givenCalendar.timeInMillis = millis

        val currentYear = currentCalendar.get(Calendar.YEAR)
        val givenYear = givenCalendar.get(Calendar.YEAR)

        return currentYear == givenYear
    }
}