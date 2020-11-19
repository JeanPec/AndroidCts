package com.shindra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shindra.arrakis.controls.Poi

class MapFragment : com.shindra.arrakis.controls.MapFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun AddPinsOnMap(MapPoints: ArrayList<Poi>){
        addPois(MapPoints)
    }

}