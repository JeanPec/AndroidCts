package com.shindra.map

import com.shindra.R
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
            context?.getString(R.string.line_a) -> R.color.LigneA
            context?.getString(R.string.line_b) -> R.color.LigneB
            context?.getString(R.string.line_c) -> R.color.LigneC
            context?.getString(R.string.line_d) -> R.color.LigneD
            context?.getString(R.string.line_e) -> R.color.LigneE
            context?.getString(R.string.line_f) -> R.color.LigneF
            else -> R.color.Body2
        }
    }
}