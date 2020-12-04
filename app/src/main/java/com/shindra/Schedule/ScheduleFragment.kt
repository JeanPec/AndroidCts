package com.shindra.Schedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.*
import com.shindra.LineMap.LineMapActivity
import com.shindra.Utilities.ErrorDialog
import com.shindra.Utilities.LoadingDialog
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop

class ScheduleFragment : Fragment() {

    private lateinit var fragmentView: View
    lateinit var recyclerView: RecyclerView
    private var listOfStop: MutableList<Stop> = mutableListOf()
    var lineTramName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initializing the components dialog
        val loadingDialog = LoadingDialog(requireActivity())
        val errorDialog = ErrorDialog(requireActivity())

        // Retrieving the name of the line
        lineTramName = arguments?.getString("lineTramName")
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.schedule_fragment, container, false)

        // Setting up the View
        recyclerView = fragmentView.findViewById(R.id.ScheduleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ScheduleAdapter(listOfStop, getString(R.string.line_name, lineTramName))


        // Calling the API
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineTramName!!, 0).observe(object :
            ObservableListener<Line> {
            override fun onLoading() {
                // Start loading here
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {
                // Stop the loading
                loadingDialog.dismiss()
                // Updating the view with the new data
                (recyclerView.adapter as ScheduleAdapter).lineStops = data.stops!!
                (recyclerView.adapter as ScheduleAdapter).notifyDataSetChanged()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                loadingDialog.dismiss()
                errorDialog.show()
            }
        })

        // Handling button click
        fragmentView.findViewById<Button>(R.id.btnShowMap).setOnClickListener {
            val intent = Intent(activity, LineMapActivity::class.java)
            intent.putExtra("lineTramName", lineTramName)
            // Redirecting to Map
            startActivity(intent)
        }

        return fragmentView
    }

}