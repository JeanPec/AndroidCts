package com.shindra.Schedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Map.MapActivity
import com.shindra.Misc.ErrorScreen
import com.shindra.Misc.LoadingScreen
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class ScheduleFragment : Fragment() {

    // Attributes
    private val TAG = "ScheduleFragment"
    private var mRecyclerView: RecyclerView? = null
    private lateinit var mTramLineLetter: String
    private lateinit var mButtonSchedule: Button

    // Methods
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // onCreateView is called after onCreate: it retrieves the view

        Log.i(TAG, "onCreateView")

        // Retrieve activity data
        mTramLineLetter = (requireArguments().getString("tramLineLetter"))!!
        (context as AppCompatActivity?)?.supportActionBar?.title = getString(R.string.line) + " " + mTramLineLetter

        // View configurations
        val view = inflater.inflate(R.layout.fv_schedule, container, false)
        mRecyclerView = view?.findViewById(R.id.fv_schedule_RecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mRecyclerView?.adapter = ScheduleAdapter(ArrayList())

        // Handle button
        mButtonSchedule = view.findViewById(R.id.fv_schedule_buttonSeeMap)
        mButtonSchedule.setOnClickListener(View.OnClickListener {
            var intent = Intent(activity, MapActivity::class.java)
            intent.putExtra("tramLineLetter", mTramLineLetter)
            startActivity(intent)
        })
        return view;
    }

    override fun onStart() {
        // onStart is called after onCreate view: it handles the logic part (network calls for us)

        Log.i(TAG, "onStart")
        super.onStart()
        var scheduleList: ArrayList<ScheduleItem> = ArrayList()
        val mLoadingScreen = LoadingScreen(context as Activity)
        val mErrorScreen = ErrorScreen(context as Activity)

        // Network calls
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, mTramLineLetter, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                Log.i(TAG, "onLoading")
                mLoadingScreen.show()
            }

            override fun onSuccess(data: Line) {
                Log.i(TAG, "onSuccess")
                mLoadingScreen.dismiss()

                for (i in 0..data.stops!!.size){
                    var sdf =  SimpleDateFormat("HH:mm");
                    var tramStationName = data.stops?.get(i)?.name

                    if(tramStationName?.length!! > 30){
                        // If name too long, short it to see the correct time
                        tramStationName = tramStationName.substring(0, 27)
                        tramStationName += ".."
                    }

                    if(i== data.stops!!.size-1) {
                        // Add the terminus one, which has no "departure time"
                        scheduleList.add(ScheduleItem("TERMINUS", tramStationName, mTramLineLetter))
                        // Add a fake one to see the last one
                        scheduleList.add(ScheduleItem("", "", ""))
                        break
                    }
                    var nextDepartureTime = sdf.format(data.stops?.get(i)?.estimatedDepartureTime).replace(':','h')
                    scheduleList.add(ScheduleItem(nextDepartureTime, tramStationName,mTramLineLetter))
                }
                // UpdateView
                var tmp = mRecyclerView?.adapter as ScheduleAdapter
                tmp.SetScheduleList(scheduleList)
            }

            override fun onError(throwable: Throwable) {
                Log.i(TAG, "onError")
                mLoadingScreen.dismiss()
                mErrorScreen.show()
            }
        })
    }

    companion object {
        fun onInstance(tramLineLetter: String): ScheduleFragment {
            val fragment = ScheduleFragment()
            val args = Bundle()
            args.putString("tramLineLetter", tramLineLetter)
            fragment.arguments = args
            return fragment
        }
    }
}
