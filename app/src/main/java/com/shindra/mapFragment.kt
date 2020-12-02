package com.shindra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*

class mapFragment : MapFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gmaps, container, false)
    }

    override fun onStart() {
        super.onStart()
        val window = loadingWindow(context)
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, arguments?.getString("lineName")!!).observe(object : ObservableListener<Line> {
            override fun onError(throwable: Throwable) {}
            override fun onSuccess(data: Line) {
                window.dismissLoadingWindow()
                val listOfPoi = ArrayList<Poi>()
                for ((_, _, _, _, position) in data.stops!!) {
                    listOfPoi.add(Poi(R.drawable.icon_maps_place_24px, getLineColorFromLine(data), position!!.latitude!!, position.longitude!!))
                }
                addPois(listOfPoi)
            }

            override fun onLoading() {
                window.displayLoadingWindow()
            }
        })
    }

    fun getLineColorFromLine(line: Line): Int {
        return when (line.name) {
            "A" -> R.color.ligneA
            "B" -> R.color.ligneB
            "C" -> R.color.ligneC
            "D" -> R.color.ligneD
            "E" -> R.color.ligneE
            "F" -> R.color.ligneF
            else -> R.color.black
        }
    }
}