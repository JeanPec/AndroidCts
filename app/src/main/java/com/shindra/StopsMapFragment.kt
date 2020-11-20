package com.shindra

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop

class StopsMapFragment : MapFragment() {
    private val logTag = "StopsMapFragment"
    private var requestedLineName : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(logTag, "Inflating fragment...")
        return inflater.inflate(R.layout.fragment_gmaps, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedLineName = requireActivity().intent.getStringExtra(TimetableFragment.LINENAME_MESSAGE).toString()
        Log.i(logTag, "Created gps fragment with tramline : " + requestedLineName)
    }

    override fun onStart() {
        super.onStart()
        //Get stops from api
        requestLineWithStops(requestedLineName)
    }

    fun requestLineWithStops(lineName: String) {
        Log.i(logTag, "RequestLineWithStops --> "+lineName)

        val loadingDialog = LoadingDialog(requireActivity())

        //Call api for available lines
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, lineName).observe(object :
                ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(logTag, "Waiting for answer...")
                loadingDialog.showLoadingScreen()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                Log.d(logTag, "Received data from network :: " + data.name + " " + data?.stops?.get(0)?.name)
                var s =  data?.stops?.get(0)
                Log.d(logTag, s?.position.toString() + " :: " + s?.position?.longitude + " :: " + s?.position?.latitude.toString())

                drawStopsOnMap(data.stops)
                loadingDialog.hideLoadingScreen()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                Log.e(logTag, "API error")
            }
        })
    }

    fun drawStopsOnMap(stops : ArrayList<Stop>?) {
        var pois = ArrayList<Poi>()
        stops?.forEach {
            pois.add(Poi(R.drawable.gps_spot_icon, R.color.black, it.position?.latitude!!, it?.position?.longitude!!))
        }
        addPois(pois)
    }
}