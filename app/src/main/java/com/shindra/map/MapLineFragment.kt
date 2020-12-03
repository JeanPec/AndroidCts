package com.shindra.map

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.*
import com.shindra.ErrorScreen
import com.shindra.LoadingScreen
import com.shindra.HelperLine
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
    private lateinit var mTramLineLetter: String

    // Methods
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // onCreateView is called after onCreate: it retrieves the view

        val view = inflater.inflate(R.layout.fragment_gmaps, container, false)

        // Retrieve activity data
        mTramLineLetter = requireArguments().getString(getString(R.string.TramLineLetterGen)).orEmpty()
        (activity as AppCompatActivity).supportActionBar?.title = "${getString(R.string.line)} $mTramLineLetter"
        return view
    }

    override fun onStart() {
        // onStart is called after onCreate view: it handles the logic part (network calls for us)
        super.onStart()
        val mLoadingScreen = LoadingScreen(context as Activity)
        val mErrorScreen = ErrorScreen(context as Activity)

        // Network calls
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM, mTramLineLetter).observe(object : ObservableListener<Line> {
                override fun onLoading() {
                    mLoadingScreen.show()
                }

                override fun onSuccess(data: Line) {
                    mLoadingScreen.dismiss()
                    updateView(data)
                }

                override fun onError(throwable: Throwable) {
                    mLoadingScreen.dismiss()
                    mErrorScreen.show()
                }
            })
    }

    // Function
    fun updateView(line: Line) {
        // Add positions on map
        val positions = ArrayList<Poi>()
        line.stops?.forEach {
            it.position?.longitude?.let { it1 -> it.position?.latitude?.let { it2 -> Poi(R.drawable.icon_maps_place_24px, HelperLine.getLineColor(mTramLineLetter), it2, it1) } }?.let { it2 -> positions.add(it2) }
        }
        addPois(positions)
    }

    // Constructor
    companion object {
        fun onInstance(tramLineLetter: String, tramLineLetterKey: String): MapLineFragment {
            val fragment = MapLineFragment()
            val args = Bundle()
            args.putString(tramLineLetterKey, tramLineLetter)
            fragment.arguments = args
            return fragment
        }
    }
}