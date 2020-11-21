package com.shindra.Map

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shindra.Misc.ErrorDialog
import com.shindra.Misc.LoadingDialog
import com.shindra.Misc.MyViewModel
import com.shindra.R
import com.shindra.Service.Converter
import com.shindra.Stop.StopFragment
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*

class MapLineFragment() : MapFragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_gmaps, container, false)

        val lineName = arguments?.getString("lineName")

        val loadingDialog = LoadingDialog(activity as Activity)
        val errorDialog = ErrorDialog(activity as Activity)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, lineName!!).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                //Show the loading dialog
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                //Dismiss the loading dialog
                loadingDialog.dismiss()

                //Add Stops icon on map
                addStopsOnMap(data)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                //Dismiss the loading dialog and show the error dialog
                loadingDialog.dismiss()
                errorDialog.show()
            }
        })

        return view
    }

    fun addStopsOnMap(line: Line?)
    {
        val pois = ArrayList<Poi>()
        for ((_, _, _, _, position) in line?.stops!!)
            pois.add(Poi(R.drawable.icon_maps_place_24px, Converter.lineLetterToLineColor(line.name), position?.latitude!!, position.longitude!!))
        addPois(pois)
    }

    companion object
    {
        fun newInstance(lineName : String?) : MapLineFragment
        {
            val fragment = MapLineFragment()
            val args = Bundle()
            args.putString("lineName", lineName)
            fragment.arguments = args
            return  fragment
        }
    }
}