package com.shindra.map

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shindra.utilis.LoadingClass
import com.shindra.utilis.MyViewModel
import com.shindra.R
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class MapWithStopFragment() : MapFragment() {

    var line_name: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        line_name = this.arguments?.getString("name")
        return inflater.inflate(R.layout.fragment_gmaps,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
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
                data.stops?.forEach { positionStops.add(Poi(R.drawable.icon_maps_place_24px, getColorId(line_name), it.position!!.latitude!!, it.position!!.longitude!!)) }
                addPois(positionStops)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }
    companion object {
        /**
         * Create a new instance of DetailsFragment, initialized to
         * show the text at 'index'.
         */
        fun newInstance(name_line: String?): MapWithStopFragment {
            val f = MapWithStopFragment()

            // Supply index input as an argument.
            val args = Bundle()
            args.putString("name", name_line)
            f.arguments = args

            return f
        }

    }

}

private fun getColorId( lineName : String?) : Int
{
    return when (lineName) {
        "A" -> R.color.Ligne_A
        "B" -> R.color.Ligne_B
        "C" -> R.color.Ligne_C
        "D" -> R.color.Ligne_D
        "E" -> R.color.Ligne_E
        "F" -> R.color.Ligne_F
        else -> {
            R.color.Primary
        }
    }
}