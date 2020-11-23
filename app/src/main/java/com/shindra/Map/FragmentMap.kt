package com.shindra.Map

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shindra.LoadingDialog
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class FragmentMap : MapFragment() {

    var lineID: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lineID = this.arguments?.getString(getString(R.string.map_activity_to_fragment_bundle_name))
    }


    override fun onStart() {
        super.onStart()

        val loadingDialog = LoadingDialog(this.activity as Activity)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        if (lineID != null) {
            model.lineWithStop(RouteType.TRAM, lineID!!).observe(object : ObservableListener<Line> {
                override fun onLoading() {
                    //call once we started the network called. Indicate that the network call is in progress
                    loadingDialog.showLoadingDialog()
                }

                override fun onSuccess(data: Line) {

                    val pois = java.util.ArrayList<Poi>()

                    data.stops?.forEach{
                        if (it.position?.latitude != null && it.position?.longitude != null && lineID != null) {
                            val poi = Poi(R.drawable.icon_maps_place_24px, getLineColor(lineID), (it.position?.latitude) as Double, (it.position?.longitude) as Double)
                            pois.add(poi)
                        }

                    }

                    addPois(pois)

                    loadingDialog.dismissLoadingDialog()
                }

                override fun onError(throwable: Throwable) {
                    //call if the network call has responded with an error
                }
            })
        }
    }

    fun  getLineColor(lineID:String?) : Int {
        return when (lineID) {
            "A" -> R.color.ligne_A
            "B" -> R.color.ligne_B
            "C" -> R.color.ligne_C
            "D" -> R.color.ligne_D
            "E" -> R.color.ligne_E
            "F" -> R.color.ligne_F
            else -> R.color.black
        }
    }

}