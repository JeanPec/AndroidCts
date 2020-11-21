package Fragments

import android.app.Activity
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

    companion object {
        fun newInstance(lineTramName: String): ScheduleFragment {
            val scheduleFragment = ScheduleFragment()

            val args = Bundle()
            args.putString("lineTramName", lineTramName)
            scheduleFragment.arguments = args

            return scheduleFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        lineTramName = arguments?.getString("lineTramName")
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.schedule_fragment, container, false)

        recyclerView = fragmentView.findViewById(R.id.ScheduleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ScheduleAdapter(listOfStop, lineTramName!!)
        Log.i("COUNT", listOfStop.toString())

        val loadingDialog = LoadingDialog(requireActivity())

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineTramName!!, 0).observe(object :
            ObservableListener<Line> {
            override fun onLoading() {
                //start loading here
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {
                loadingDialog.dismiss()

                Log.i("COUNT", data.stops.toString())
                // Complex expression ??
                (recyclerView.adapter as ScheduleAdapter).lineStops = data.stops!!
                (recyclerView.adapter as ScheduleAdapter).notifyDataSetChanged()
                // Stop loading

            }

            override fun onError(throwable: Throwable) {
                // Stop loading + launch error
                loadingDialog.dismiss()

            }
        })

        fragmentView.findViewById<Button>(R.id.btnShowMap).setOnClickListener {
            val intent = Intent(activity, LineMapActivity::class.java)
            intent.putExtra("lineTramName", lineTramName)
            startActivity(intent)
        }

        return fragmentView
    }



}