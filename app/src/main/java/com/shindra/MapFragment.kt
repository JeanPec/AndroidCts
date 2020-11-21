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

class MapFragment : MapFragment() {
    private val logTag = "StopsMapFragment"
    private var requestedLineName : String = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_gmaps, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedLineName = requireActivity().intent.getStringExtra(Schedule__Fragment.LINENAME_MESSAGE).toString()
    }

    override fun onStart() {
        super.onStart()
        requestLineWithStops(requestedLineName)
    }

    fun requestLineWithStops(lineName: String) {
        val loadingDialog = LoadingDialog(requireActivity())

        //Call api for available lines
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, lineName).observe(object :
                ObservableListener<Line> {
            override fun onLoading() {
                loadingDialog.showDialog()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success

                var s =  data?.stops?.get(0)

                drawStopsOnMap(data.stops)
                loadingDialog.dismissDialog()
            }

            override fun onError(throwable: Throwable) {

            }
        })
    }

    fun drawStopsOnMap(stops : ArrayList<Stop>?) {
        var pois = ArrayList<Poi>()
        stops?.forEach {
            pois.add(Poi(R.drawable.icon_maps_place_24px, R.color.black, it.position?.latitude!!, it?.position?.longitude!!))
        }
        addPois(pois)
    }
}