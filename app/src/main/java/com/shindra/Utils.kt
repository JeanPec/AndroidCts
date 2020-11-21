package com.shindra

fun GetColorIDFromLine(l : String) : Int {
    return when(l) {
        LineA -> R.color.LineA
        LineB -> R.color.LineB
        LineC -> R.color.LineC
        LineD -> R.color.LineD
        LineE -> R.color.LineE
        LineF -> R.color.LineF
        else -> R.color.black
    }
}