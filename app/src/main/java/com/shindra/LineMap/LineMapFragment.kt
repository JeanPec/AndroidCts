package com.shindra.LineMap

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shindra.*
import com.shindra.Utilities.ApiLinesConvertor
import com.shindra.Utilities.ErrorDialog
import com.shindra.Utilities.LoadingDialog
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class LineMapFragment : MapFragment() {

    private lateinit var fragmentView: View
    var lineTramName: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Retrieving the name of the line
        lineTramName = arguments?.getString("lineTramName")
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_gmaps, container, false)
        // Rest of the initialization is already done in MapFragment

        return fragmentView
    }

    // onStart from MapFragment
    override fun onStart() {
        // Called once onCreate is done
        super.onStart()

        val loadingDialog = LoadingDialog(activity as Activity)
        val errorDialog = ErrorDialog(requireActivity())

        // Calling the API
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, lineTramName!!).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                loadingDialog.dismiss()
                addStop(data)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                loadingDialog.dismiss()
                errorDialog.show()
            }
        })

    }

    fun addStop(line: Line) {
        // Adding position/points on the map
        val pois = java.util.ArrayList<Poi>()
        for (stop in line.stops!!) {
            pois.add(Poi(R.drawable.icon_maps_place_24px, ApiLinesConvertor().lineToColor(lineTramName!!), stop.position?.latitude!!, stop.position?.longitude!!))
        }
        // This will update the view with the new points
        addPois(pois)
    }

}