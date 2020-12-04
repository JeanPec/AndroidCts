package com.shindra.Utilities

import com.shindra.R
import com.shindra.ctslibrary.bo.Line
import java.text.SimpleDateFormat
import java.util.*

class ApiLinesConvertor {

    // Converts line letter to the matching icon
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

    // Parse the date to the format we want
    fun getDateText(date: Date?): String {
        return if(date === null) {
            "N/A"
        } else {
            val simpleDateFormat = SimpleDateFormat("HH'h'mm", Locale.FRANCE)
            simpleDateFormat.format(date)
        }
    }

    // Converts the line letter to the matching color
    fun lineToColor(lineName: String) : Int {
        return when (lineName) {
            "A", "Ligne A" -> R.color.line_a
            "B", "Ligne B" -> R.color.line_b
            "C", "Ligne C" -> R.color.line_c
            "D", "Ligne D" -> R.color.line_d
            "E", "Ligne E" -> R.color.line_e
            "F", "Ligne F" -> R.color.line_f
            else -> R.color.body2
        }
    }
}