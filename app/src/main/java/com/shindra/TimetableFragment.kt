package com.shindra

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class TimetableFragment : Fragment() {
    private val TAG = "TramsSchedulesFragment"
    var associatedTramline: Line? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Inflating fragment...")
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
    }


    fun RequestTimetable(lineName : String) {
        //Call api for available lines
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineName, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(TAG, "Waiting for answer...")
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                Log.i(TAG, "Received data from network :: "+data.name)
                associatedTramline = data
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                Log.e(TAG, "API error")
            }
        })
    }
}