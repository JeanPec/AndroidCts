package com.shindra

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.apibo.Coordinate
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class TimeFragment : Fragment() {

    fun newInstance(): TimeFragment{
        return TimeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listStop = ArrayList<Stop>()
        val line_name= this.arguments?.getString("name")
        val stops_recylcer_view = this.activity?.findViewById<RecyclerView>(R.id.fragment_container)
        if (stops_recylcer_view != null) {
            stops_recylcer_view.adapter = TimeAdapter(listStop, line_name)
            stops_recylcer_view.layoutManager = LinearLayoutManager(activity )
            stops_recylcer_view.setHasFixedSize(true)
        }


    }

    fun setListStop(list : ArrayList<Stop>){
        val stops_recylcer_view = this.activity?.findViewById<RecyclerView>(R.id.fragment_container)
        if (stops_recylcer_view != null) {
            (stops_recylcer_view.adapter as TimeAdapter).stopList = list
            (stops_recylcer_view.adapter as TimeAdapter).notifyDataSetChanged()
        }
    }

}
