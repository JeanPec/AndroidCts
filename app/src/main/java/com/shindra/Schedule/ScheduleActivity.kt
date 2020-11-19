package com.shindra.Schedule

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Map.MapActivity
import com.shindra.MapClick
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class ScheduleActivity : AppCompatActivity(), MapClick {

    val stops = ArrayList<Stop>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val lineID = intent.getStringExtra("LINE")!!
        println("OUT $lineID")

        setContentView(R.layout.fragment_schedule)
        title = "Ligne $lineID"

        val loadingDialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog,null)
        val dialogBuilder = AlertDialog.Builder(this).setView(loadingDialogView)
        val loadingDialog = dialogBuilder.create()

        val scheduleRecyclerList = findViewById<RecyclerView>(R.id.cardListSchedule)
        scheduleRecyclerList.layoutManager = LinearLayoutManager(this)
        scheduleRecyclerList.adapter = ScheduleRecyclerViewAdapter(stops, lineID)

        val button = findViewById<Button>(R.id.map_button)
        button.setOnClickListener(View.OnClickListener { onMapClick(lineID) })

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineID, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {
                val stops = ArrayList<Stop>()
                for (stop in data.stops!!) {
                    stops.add(stop)
                }
                (scheduleRecyclerList.adapter as ScheduleRecyclerViewAdapter).stops = stops
                (scheduleRecyclerList.adapter as ScheduleRecyclerViewAdapter).notifyDataSetChanged()

                loadingDialog.dismiss()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }

    override fun onMapClick(lineID: String) {
        val intent = Intent(this@ScheduleActivity, MapActivity::class.java)
        println(lineID)
        intent.putExtra("LINE_ID", lineID)
        startActivity(intent)
    }

}