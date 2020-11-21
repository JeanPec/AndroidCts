package com.shindra.schedules

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.map.MapLineActivity
import com.shindra.utilities.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import com.shindra.utilities.LoadingDialog
import kotlin.collections.ArrayList

class ScheduleFragmentStop() : Fragment(){
    private var stopList = ArrayList<Stop>()

    fun newInstance(lineName : String): ScheduleFragmentStop {
        val fragment = ScheduleFragmentStop()
        val bundle = Bundle()
        bundle.putString("lineName", lineName)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.schedule_fragment_stop, container, false)

        //data recovery
        val lineName = arguments?.getString("lineName",context?.getString(R.string.schedule_card_view_line_name_default))

        //RecyclerView configuration
        val stopRecyclerList = view.findViewById<RecyclerView>(R.id.stop_list)
        stopRecyclerList.layoutManager = LinearLayoutManager(activity)
        stopRecyclerList.adapter = lineName?.let { ScheduleAdapter(it,stopList,activity) }

        //set Button listener
        view.findViewById<Button>(R.id.button_map).setOnClickListener{
            //Intent creation and new Activity launch
            val intent = Intent(activity, MapLineActivity::class.java)
            intent.putExtra("lineName", lineName)
            startActivity(intent)
        }

        //LoadingDialog creation
        val loadingDialog = LoadingDialog(activity as FragmentActivity)

        //network call configuration
        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        //perform network call
        lineName?.let {
            model.lineWithEstimatedTimeTable(RouteType.TRAM, it,0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                //display LoadingDialog
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                //adapter data change
                val stopsList = ArrayList<Stop>()
                data.stops?.forEach { it1 ->
                    if (it1.estimatedDepartureTime != null){
                        stopsList.add(it1)
                    }
                }
                (stopRecyclerList.adapter as ScheduleAdapter).stops = stopsList
                (stopRecyclerList.adapter as ScheduleAdapter).notifyDataSetChanged()

                //remove LoadingDialog
                loadingDialog.dismiss()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                //display error message
                Toast.makeText(activity, R.string.loading_text_error, Toast.LENGTH_LONG).show()

                //remove LoadingDialog
                loadingDialog.dismiss()
            }
            })
        }

        return view
    }
}