package Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.ScheduleAdapter
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import kotlin.reflect.typeOf

class ScheduleFragment : Fragment() {

    private lateinit var fragmentView: View
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ScheduleAdapter
    private var listOfStop: MutableList<Stop> = mutableListOf()
    var lineTramName: String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        lineTramName = arguments?.getString("lineTramName")
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.schedule_fragment, container, false)

        recyclerView = fragmentView.findViewById(R.id.ScheduleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ScheduleAdapter(listOfStop, lineTramName!!)
        Log.i("COUNT", listOfStop.toString())

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineTramName!!, 0).observe(object: ObservableListener<Line> {
            override fun onLoading() {
                //start loading here
            }

            override fun onSuccess(data: Line) {
                Log.i("COUNT", data.stops.toString())
                // Complex expression ??
                (recyclerView.adapter as ScheduleAdapter).lineStops = data.stops!!
                (recyclerView.adapter as ScheduleAdapter).notifyDataSetChanged()
                // Stop loading
            }

            override fun onError(throwable: Throwable) {
                // Stop loading + launch error
            }
        })

        return fragmentView
    }
}