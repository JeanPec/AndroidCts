package com.shindra

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line


private const val ARG_PARAM1 = "line"

/**
 * A simple [Fragment] subclass.
 * Use the [LigneMapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LineMapFragment : MapFragment() {

    private var line: String? = null
    private var lineObject : Line? = null
    private var listPosition : ArrayList<Poi>? = null
    private var icon : Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            line = it.getString(ARG_PARAM1)
        }

    }

    override fun onStart() {
        super.onStart()
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, line!!).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress

            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                lineObject = data
                listPosition = ArrayList<Poi>()
                lineObject?.stops?.forEach {stop ->
                    context?.let{
                        if(stop.position?.latitude != null && stop.position?.longitude != null){
                            var poi = Poi(getImageId(it,"icon_maps_place_24px"),getColorId(it,"Ligne"+line), stop?.position?.latitude!!,stop?.position?.longitude!!)
                        listPosition?.add(poi)
                        }
                    }
                    //var poi = Poi(getImageId(context,"icon_maps_place_24px"),getColorId(context!!,"Ligne"+line), it.position?.latitude!!,it.position?.longitude!!)
                }
                listPosition?.let {  addPois(it) }
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }
    fun getImageId(context: Context, pictureName: String) = context.getResources().getIdentifier("drawable/$pictureName", null, context.getPackageName())
    fun getColorId(context: Context, colorName: String) = context.getResources().getIdentifier("$colorName", "color", context.getPackageName())


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param line get current line.
         * @return A new instance of fragment LigneMapFragment.
         */
        @JvmStatic
        fun newInstance(line: String?) =
                LineMapFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, line)
                    }
                }
    }
}