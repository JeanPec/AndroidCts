package com.shindra

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class TimetableFragment : Fragment() {
    private val TAG = "TramsSchedulesFragment"
    var associatedTramline: Line? = null
    private var stops: ArrayList<Stop>? = null

    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Inflating fragment...")

        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

    }

    override fun onPause() {
        super.onPause()
    }


    fun RequestTimetable(lineName: String) {
        Log.i(TAG, "RequestTimetable")

        SetUpRecyclerView()

        //Call api for available lines
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineName, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(TAG, "Waiting for answer...")
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                Log.i(TAG, "Received data from network :: " + data.name)
                associatedTramline = data
                stops?.clear()
                stops?.addAll(data.stops!!)

                //Fill the recycler view with received tramlines
                recyclerView?.adapter?.notifyDataSetChanged()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                Log.e(TAG, "API error")
            }
        })
    }


    private fun SetUpRecyclerView(): RecyclerView? {
        Log.i(TAG, "setUpRecyclerView")

        stops = ArrayList() //empty list to initialize recycler view

        recyclerView = view?.findViewById<RecyclerView>(R.id.rvTimetable)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = TimetableRecyclerViewAdaptater(stops!!)

        return recyclerView
    }
}