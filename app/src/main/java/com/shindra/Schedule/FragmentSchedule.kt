package com.shindra.Schedule

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Map.MapActivity
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.ArrayList

class FragmentSchedule : Fragment() {

    private lateinit var viewOfLayout: View
    val stops = ArrayList<Stop>()
    var lineID: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewOfLayout =  inflater.inflate(R.layout.fragment_schedule, container, false)
        lineID = this.arguments?.getString("LINE_ID")
        return viewOfLayout
    }

    override fun onStart() {
        super.onStart()

        val loadingDialogView = LayoutInflater.from(this.context).inflate(R.layout.loading_dialog,null)
        val dialogBuilder = AlertDialog.Builder(this.context).setView(loadingDialogView)
        val loadingDialog = dialogBuilder.create()

        val scheduleRecyclerList = viewOfLayout.findViewById<RecyclerView>(R.id.cardListSchedule)
        scheduleRecyclerList.layoutManager = LinearLayoutManager(this.context)
        scheduleRecyclerList.adapter = lineID?.let { ScheduleRecyclerViewAdapter(stops, it) }

        val button = viewOfLayout.findViewById<Button>(R.id.map_button)
        button.setOnClickListener(View.OnClickListener { lineID?.let { it1 -> onMapClick(it1) } })

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        lineID?.let {
            model.lineWithEstimatedTimeTable(RouteType.TRAM, it, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {

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
    }

    private fun onMapClick(lineID: String) {
        val intent = Intent(this.activity, MapActivity::class.java)
        println(lineID)
        intent.putExtra("LINE_ID", lineID)
        startActivity(intent)
    }

}