package com.shindra.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.R
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.utilities.LoadingDialog
import com.shindra.utilities.MyViewModel

class MapLineFragment : MapFragment() {
    private var lineName = String()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gmaps, container, false)

        //data recovery
        lineName = arguments?.getString("lineName",context?.getString(R.string.schedule_card_view_line_name_default)).toString()

        return view
    }

    override fun onStart() {
        super.onStart()

        //LoadingDialog creation
        val loadingDialog = LoadingDialog(activity as FragmentActivity)

        //network call configuration
        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        //perform network call
        model.lineWithStop(RouteType.TRAM,lineName).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                //display LoadingDialog
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                //add point on map
                val coordinateList = ArrayList<Poi>()
                data.stops?.forEach {
                    coordinateList.add(Poi(R.drawable.icon_maps_place_24px, getLineColor(lineName), it.position?.latitude!!, it.position?.longitude!!))
                }
                addPois(coordinateList)

                //remove LoadingDialog
                loadingDialog.dismiss()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                //display error message
                Toast.makeText(activity, R.string.loading_text_error, Toast.LENGTH_LONG).show()

                //remove LoadingDialog
                loadingDialog.dismiss()
            }
        })
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