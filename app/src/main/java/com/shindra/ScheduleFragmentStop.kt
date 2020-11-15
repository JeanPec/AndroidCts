package com.shindra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class ScheduleFragmentStop : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.schedule_fragment_stop, container, false)
        val stopList = view.findViewById<RecyclerView>(R.id.stop_list)
        val lineName = arguments?.getString("lineName",context?.getString(R.string.schedule_default_line_name))
        stopList.layoutManager = LinearLayoutManager(activity)
        stopList.adapter = lineName?.let { ScheduleAdapter(it, listOfStop, activity) }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private val listOfStop: ArrayList<Stop>
        get() {
            val stopArray = ArrayList<Stop>()
            val time = Date()
            stopArray.add(Stop("Arret A", time, time,"Direction"))
            stopArray.add(Stop("Arret B", time, time,"Direction"))
            stopArray.add(Stop("Arret C", time, time,"Direction"))
            stopArray.add(Stop("Arret D", time, time,"Direction"))
            stopArray.add(Stop("Arret E", time, time,"Direction"))
            stopArray.add(Stop("Arret F", time, time,"Direction"))
            return stopArray
        }
}