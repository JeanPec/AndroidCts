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

class StopFragment : Fragment() {
    private val logTag = "TramsSchedulesFragment"

    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.stop_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun requestTimetable(lineName: String) {

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineName, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
            }

            override fun onSuccess(data: Line) {
                setUpRecyclerView(data)
            }

            override fun onError(throwable: Throwable) {
                Log.e(logTag, "API error")
            }
        })
    }


    private fun setUpRecyclerView(lineWithTimetable: Line): RecyclerView? {
        Log.i(logTag, "setUpRecyclerView")

        recyclerView = view?.findViewById(R.id.ScheduleFragment)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = TimetableRecyclerViewAdaptater(lineWithTimetable, requireContext())

        return recyclerView
    }
}