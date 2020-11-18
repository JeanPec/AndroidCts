package com.shindra.Time

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Map.MapActivity
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import kotlin.collections.ArrayList

class TimeFragment : Fragment() {

    fun newInstance(): TimeFragment {
        return TimeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.time_fragment_stop, container, false)
        val listStop = ArrayList<Stop>()
        val line_name= this.arguments?.getString("name")
        val stops_recylcer_view = view.findViewById<RecyclerView>(R.id.stop_container)
        stops_recylcer_view.adapter = TimeAdapter(listStop, line_name )
        stops_recylcer_view.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         view.findViewById<Button>(R.id.button_map).setOnClickListener{
             val intent = Intent(activity, MapActivity::class.java)
             intent.putExtra("name", view.findViewById<TextView>(R.id.text_body_line).text)
             //intent.putExtra
             startActivity(intent)
         }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun setListStop(list : ArrayList<Stop>){
        val stops_recylcer_view = this.activity?.findViewById<RecyclerView>(R.id.stop_container)
        (stops_recylcer_view?.adapter as TimeAdapter).stopList = list
        (stops_recylcer_view?.adapter as TimeAdapter).notifyDataSetChanged()
    }



}
