package com.shindra.Map

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.shindra.LoadingClass
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class MapWithStopFragment : MapFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val line_name = this.arguments?.getString("name")

        val dialog = LoadingClass(activity as Activity)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, line_name!!).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                dialog.show()
            }

            override fun onSuccess(data: Line) {
                dialog.dismiss()
                val positionStops = ArrayList<Poi>()
                //call once the network call has responded with a success
                data.stops!!.forEach { positionStops.add(Poi(R.drawable.icon_maps_place_24px, getColorId(line_name), it.position!!.latitude!!, it.position!!.longitude!!)) }
                addPois(positionStops)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }

}

private fun getColorId( lineName : String?) : Int
{
    when (lineName) {
        "A" -> return R.color.Ligne_A
        "B" -> return R.color.Ligne_B
        "C" -> return R.color.Ligne_C
        "D" -> return R.color.Ligne_D
        "E" -> return R.color.Ligne_E
        "F" -> return R.color.Ligne_F
        else -> {
            return R.color.Primary
        }
    }
}