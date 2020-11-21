package com.shindra

import android.graphics.drawable.Drawable
import android.util.Log
import com.shindra.ctslibrary.bo.Line
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class ApiLinesConvertor {

    fun apiLineToIcon(apiLine: Line): Int {
        return when(apiLine.name) {
            "A" -> R.drawable.ic_tram_a
            "B" -> R.drawable.ic_tram_b
            "C" -> R.drawable.ic_tram_c
            "D" -> R.drawable.ic_tram_d
            "E" -> R.drawable.ic_tram_e
            "F" -> R.drawable.ic_tram_f
            else -> R.drawable.ic_tram
        }
    }

    fun getDateText(date: Date?): String {
        return if(date === null) {
            "N/A"
        } else {
            val simpleDateFormat = SimpleDateFormat("h'h'mm", Locale.FRANCE)
            simpleDateFormat.format(date)
        }
    }

    fun lineToColor(lineName: String) : Int {
        return when (lineName) {
            "A" -> R.color.line_a
            "B" -> R.color.line_b
            "C" -> R.color.line_c
            "D" -> R.color.line_d
            "E" -> R.color.line_e
            "F" -> R.color.line_f
            else -> R.color.body2
        }
    }

}