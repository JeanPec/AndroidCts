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
import com.shindra.ctslibrary.bo.Stop
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class TimeFragment : Fragment() {

    fun newInstance(): TimeFragment{
        return TimeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val line_name= this.arguments?.getString("name")

        val listStop :ArrayList<Stop> = initList()

        val stops = this.activity?.findViewById<RecyclerView>(R.id.fragment_container)
        if (stops != null) {
            stops.adapter = TimeAdapter(listStop, line_name)
            stops.layoutManager = LinearLayoutManager(activity )
            stops.setHasFixedSize(true)
        }
    }

}

private fun initList(): ArrayList<Stop>{

    val list = ArrayList<Stop>()
    list += Stop("Berlioz", Date(12), Date(14),"devant", Coordinate(12.3,13.3))
    list += Stop("Anemones",Date(12), Date(24),"devant", Coordinate(14.3,93.3))
    return list

}