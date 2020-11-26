package com.shindra

// Class used as a group of functions
object HelperLine {
    fun getTramLetterImg(tramLetter: String?): Int {
        return when (tramLetter) {
            "A" -> R.drawable.tram_a
            "B" -> R.drawable.tram_b
            "C" -> R.drawable.tram_c
            "D" -> R.drawable.tram_d
            "E" -> R.drawable.tram_e
            "F" -> R.drawable.tram_f
            else -> R.drawable.ic_launcher_background
        }
    }

    fun getLineColor(tramLetter: String?): Int {
        return when (tramLetter) {
            "A" -> R.color.LigneA
            "B" -> R.color.LigneB
            "C" -> R.color.LigneC
            "D" -> R.color.LigneD
            "E" -> R.color.LigneE
            "F" -> R.color.LigneF
            else -> R.color.Headline
        }
    }
}