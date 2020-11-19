package com.shindra

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.controls.MapFragment

class StopsMapFragment : MapFragment() {
    private val logTag = "StopsMapFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(logTag, "Inflating fragment...")
        return inflater.inflate(R.layout.fragment_gmaps, container, false)
    }


}