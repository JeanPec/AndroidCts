package com.shindra

import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.ctslibrary.bo.Line

class MapLineFragment : MapFragment() {
    fun addPointOnMap(line: Line){
        val positionList = ArrayList<Poi>()
        for (stop in line.stops!!){
            val latitude = stop.position?.latitude
            val longitude = stop.position?.longitude
            positionList.add(Poi(R.drawable.icon_maps_place_24px, getLineColor(line.name), latitude!!, longitude!!))
        }
        addPois(positionList)
    }

    private fun getLineColor(line: String) : Int {
        return when (line){
            "Parc des Sports - Illkirch Graffenstaden" -> R.color.LigneA
            "Lingolsheim Tiergaertel - Hoenheim Gare" -> R.color.LigneB
            "Gare Centrale - Neuhof Rodolphe Reuss" -> R.color.LigneC
            "Poteries - Port du Rhin / Kehl Rathaus" -> R.color.LigneD
            "Robertsau l'Escale - Campus d'Illkirch" -> R.color.LigneE
            "Comtes - Place d'Islande" -> R.color.LigneF
            else -> R.color.Body2
        }
    }
}