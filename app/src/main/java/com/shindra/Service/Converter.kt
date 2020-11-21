package com.shindra.Service

import com.shindra.R
import java.text.SimpleDateFormat
import java.util.*

object Converter {
    fun lineLetterToLineIcon(lineLetter: String?): Int
    {
        return when (lineLetter) {
            "A" -> R.drawable.tram_a
            "B" -> R.drawable.tram_b
            "C" -> R.drawable.tram_c
            "D" -> R.drawable.tram_d
            "E" -> R.drawable.tram_e
            "F" -> R.drawable.tram_f
            else -> R.drawable.tram
        }
    }

    fun lineLetterToLineColor(lineLetter: String?): Int
    {
        return when (lineLetter) {
            "A" -> R.color.LineA
            "B" -> R.color.LineB
            "C" -> R.color.LineC
            "D" -> R.color.LineD
            "E" -> R.color.LineE
            "F" -> R.color.LineF
            else -> R.color.black
        }
    }

    fun dateToTime(date: Date?): String
    {
        val simpleDateFormat = SimpleDateFormat("HH'h'mm")
        return simpleDateFormat.format(date)
    }
}