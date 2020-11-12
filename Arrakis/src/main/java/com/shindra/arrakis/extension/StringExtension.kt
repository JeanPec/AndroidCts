package com.shindra.arrakis.extension

import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*

fun String.toDate(format : String): Date? {
    return SimpleDateFormat(format, Locale.ENGLISH).parse(this)
}