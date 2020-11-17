package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class ScheduleActivity : AppCompatActivity() {


    val stops = ArrayList<Stop>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val lineName = intent.getStringExtra("LINE")
        val lineID: String
        println("OUT $lineName")
        lineID = if ("Parc des Sports - Illkirch Graffenstaden" == lineName) {
            "A"
        } else if ("Lingolsheim Tiergaertel - Hoenheim Gare" == lineName) {
            "B"
        } else if ("Gare Centrale - Neuhof Rodolphe Reuss" == lineName) {
            "C"
        } else if ("Poteries - Port du Rhin / Kehl Rathaus" == lineName) {
            "D"
        } else if ("Robertsau l'Escale - Campus d'Illkirch" == lineName) {
            "E"
        } else if ("Comtes - Place d'Islande" == lineName) {
            "F"
        } else {
            ""
        }
        setContentView(R.layout.fragment_schedule)
        title = "Ligne $lineID"

        /*private var ScheduleRecyclerView: RecyclerView? = null
        private var ScheduleAdapter: RecyclerView.Adapter<*>? = null
        private var ScheduleLayoutManager: RecyclerView.LayoutManager? = null*/

        val scheduleRecyclerList = findViewById<RecyclerView>(R.id.cardListSchedule)
        scheduleRecyclerList.layoutManager = LinearLayoutManager(this)
        scheduleRecyclerList.adapter = ScheduleRecyclerViewAdapter(stops, lineID)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineID, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            override fun onSuccess(data: Line) {
                val stops = ArrayList<Stop>()
                for (stop in data.stops!!) {
                    stops.add(stop)
                    //ScheduleRecyclerView.setAdapter(ScheduleAdapter);
                }
                /*ScheduleAdapter = ScheduleRecyclerViewAdapter(stops, lineID)
                ScheduleRecyclerView!!.layoutManager = ScheduleLayoutManager
                ScheduleRecyclerView!!.adapter = ScheduleAdapter*/


                (scheduleRecyclerList.adapter as ScheduleRecyclerViewAdapter).stops = stops
                (scheduleRecyclerList.adapter as ScheduleRecyclerViewAdapter).notifyDataSetChanged()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
        /*ScheduleRecyclerView = findViewById(R.id.cardListSchedule)
        ScheduleRecyclerView.setHasFixedSize(true)
        ScheduleLayoutManager = LinearLayoutManager(this)*/
    }
}