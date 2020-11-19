package com.shindra.Time

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.LoadingClass
import com.shindra.Map.MapActivity
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import kotlin.collections.ArrayList

class TimeFragment : Fragment() {

    fun newInstance(): TimeFragment {
        return TimeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view= inflater.inflate(R.layout.time_fragment_stop, container, false)
        val stops_recylcer_view = view.findViewById<RecyclerView>(R.id.stop_container)
        stops_recylcer_view.layoutManager = LinearLayoutManager(activity)

        val line_name = this.arguments?.getString("name")

        val dialog = LoadingClass(activity as Activity)
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, line_name!!,0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                dialog.show()
            }

            override fun onSuccess(data: Line) {
                dialog.dismiss()
                //call once the network call has responded with a success
                stops_recylcer_view.adapter = TimeAdapter(data.stops!!, line_name )
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         view.findViewById<Button>(R.id.button_map).setOnClickListener{
             val intent = Intent(activity, MapActivity::class.java)
             intent.putExtra("name", this.arguments?.getString("name"))
             startActivity(intent)
         }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
