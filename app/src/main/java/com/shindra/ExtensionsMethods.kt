package com.shindra

import java.util.*

fun Date.getDateStringHHMM(): String {
    val date: Date = this // your date
    val cal = Calendar.getInstance()
    cal.time = date
    val hours = cal.get(Calendar.HOUR_OF_DAY).toString()
    val minutes = String.format("%02d", cal.get(Calendar.MINUTE))
    return (hours + "h" + minutes)
}