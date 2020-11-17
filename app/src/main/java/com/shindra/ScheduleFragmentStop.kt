package com.shindra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import kotlin.collections.ArrayList

class ScheduleFragmentStop() : Fragment(){

    private var stopList = ArrayList<Stop>()
    private lateinit var stopRecyclerList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.schedule_fragment_stop, container, false)
        stopRecyclerList = view.findViewById<RecyclerView>(R.id.stop_list)
        val lineName = arguments?.getString("lineName",context?.getString(R.string.schedule_card_view_line_name_default))

        stopRecyclerList.layoutManager = LinearLayoutManager(activity)
        stopRecyclerList.adapter = lineName?.let { ScheduleAdapter(it,stopList,activity) }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_map).setOnClickListener{

        }
    }

    fun addStop(line: Line) {
        (stopRecyclerList.adapter as ScheduleAdapter).stops = line.stops!!
        (stopRecyclerList.adapter as ScheduleAdapter).notifyDataSetChanged()
    }
}