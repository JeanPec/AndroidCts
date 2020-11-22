package com.shindra.Map

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.*
import com.shindra.Misc.ErrorScreen
import com.shindra.Misc.LoadingScreen
import com.shindra.Others.HelperLine
import com.shindra.arrakis.controls.MapFragment
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*

/* Kotlin remember:
 *
 * In Kotlin, convention for functions/methods is to start with lower case
 *
 * Using ?. on an object method is called "safe call". You can chain it to multiple calls like:
 * (context as AppCompatActivity?)?.supportActionBar?.title. Each time, it will check if returned
 * value is null or not, without any crash. If a null value is detected, it returns null and after
 * we can handle the exception.
 *
 * A "companion" object is equivalent to "static" in JAVA: onInstance is made companion, because
 * it's a method used only on the class and not on an object of the class.
 *
 */

class MapLineFragment : MapFragment() {

    // Attributes
    private val TAG = "MapLineFragment"
    private lateinit var mTramLineLetter: String

    // Methods
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // onCreateView is called after onCreate: it retrieves the view

        Log.i(TAG, "onCreateView")
        val view = inflater.inflate(R.layout.fragment_gmaps, container, false)

        // Retrieve activity data
        mTramLineLetter = (requireArguments().getString("tramLineLetter"))!!
        (context as AppCompatActivity?)?.supportActionBar?.title = getString(R.string.line) + " " + mTramLineLetter
        return view
    }

    override fun onStart() {
        // onStart is called after onCreate view: it handles the logic part (network calls for us)

        Log.i(TAG, "onStart")
        super.onStart()
        val mLoadingScreen = LoadingScreen(context as Activity)
        val mErrorScreen = ErrorScreen(context as Activity)

        // Network calls
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, mTramLineLetter).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                Log.i(TAG, "onLoading")
                mLoadingScreen.show()
            }

            override fun onSuccess(data: Line) {
                Log.i(TAG, "onSuccess")
                mLoadingScreen.dismiss()
                updateView(data)
            }

            override fun onError(throwable: Throwable) {
                Log.i(TAG, "onError")
                mLoadingScreen.dismiss()
                mErrorScreen.dismiss()
            }
        })
    }

    // Function
    fun updateView(line: Line) {
        // Add positions on map
        Log.i(TAG, "updateView")
        val positions = ArrayList<Poi>()
        line.stops?.forEach {
            positions.add(Poi(R.drawable.icon_maps_place_24px, HelperLine.GetLineColor(mTramLineLetter), it.position?.latitude!!, it.position?.longitude!!))
        }
        addPois(positions)
    }

    // Constructor
    companion object {
        fun onInstance(tramLineLetter: String): MapLineFragment {
            val fragment = MapLineFragment()
            val args = Bundle()
            args.putString("tramLineLetter", tramLineLetter)
            fragment.arguments = args
            return fragment
        }
    }
}