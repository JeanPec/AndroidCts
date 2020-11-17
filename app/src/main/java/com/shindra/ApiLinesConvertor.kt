package com.shindra

import android.graphics.drawable.Drawable
import android.util.Log
import com.shindra.ctslibrary.bo.Line

class ApiLinesConvertor {

    private val A = "Parc des Sports - Illkirch Graffenstaden"
    private val B = "Lingolsheim Tiergaertel - Hoenheim Gare"
    private val C = "Gare Centrale - Neuhof Rodolphe Reuss"
    private val D = "Poteries - Port du Rhin / Kehl Rathaus"
    private val E = "Robertsau l'Escale - Campus d'Illkirch"
    private val F = "Comtes - Place d'Islande"

    public fun apiLineToLetter(apiLine: Line):  Char {
        return when(apiLine.name) {
            A -> 'A'
            B -> 'B'
            C -> 'C'
            D -> 'D'
            E -> 'E'
            F -> 'F'
            else -> 'Z'
        }
    }

    public fun apiLineToIcon(apiLine: Line): Int {
        return when(apiLine.name) {
            A -> R.drawable.ic_tram_a
            B -> R.drawable.ic_tram_b
            C -> R.drawable.ic_tram_c
            D -> R.drawable.ic_tram_d
            E -> R.drawable.ic_tram_e
            F -> R.drawable.ic_tram_f
            else -> R.drawable.ic_tram
        }
    }

}