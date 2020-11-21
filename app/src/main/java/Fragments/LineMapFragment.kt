package Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shindra.ApiLinesConvertor
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class LineMapFragment : MapFragment() {

    private lateinit var fragmentView: View
    var lineTramName: String? = null

    companion object {
        fun newInstance(lineTramName: String): LineMapFragment {
            val lineMapFragment = LineMapFragment()

            val args = Bundle()
            args.putString("lineTramName", lineTramName)
            lineMapFragment.arguments = args

            return lineMapFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        lineTramName = arguments?.getString("lineTramName")
        fragmentView = inflater.inflate(R.layout.fragment_gmaps, container, false)
        // Rest of the initialization is already done in MapFragment

        return fragmentView
    }

    // onStart from MapFragment
    override fun onStart() {
        super.onStart()

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, lineTramName!!).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                addStop(data)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })

    }
    fun addStop(line: Line) {

        val pois = java.util.ArrayList<Poi>()
        for (stop in line.stops!!) {
            pois.add(Poi(R.drawable.icon_maps_place_24px, ApiLinesConvertor().lineToColor(lineTramName!!), stop.position?.latitude!!, stop.position?.longitude!!))
        }

        addPois(pois)
    }

}