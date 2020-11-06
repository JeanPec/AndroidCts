package com.shindra.arrakis.extension

import java.time.Duration
import java.util.*


fun Date.isValid(duration: Duration) : Boolean{
    val now =  Calendar.getInstance().time.time
    val expirationDate = this.time + duration.toMillis()

    return now < expirationDate
}