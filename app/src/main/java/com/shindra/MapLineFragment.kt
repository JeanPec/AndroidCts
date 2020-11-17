package com.shindra

import androidx.core.content.ContextCompat
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
            "A" -> R.color.LigneA
            "B" -> R.color.LigneB
            "C" -> R.color.LigneC
            "D" -> R.color.LigneD
            "E" -> R.color.LigneE
            "F" -> R.color.LigneF
            else -> R.color.Body2
        }
    }
}