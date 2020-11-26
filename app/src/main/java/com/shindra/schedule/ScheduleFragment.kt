package com.shindra.schedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.map.MapActivity
import com.shindra.ErrorScreen
import com.shindra.LoadingScreen
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleFragment : Fragment() {

    // Attributes
    private var mRecyclerView: RecyclerView? = null
    private lateinit var mTramLineLetter: String
    private lateinit var mButtonSchedule: Button

    // Methods
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // onCreateView is called after onCreate: it retrieves the view

        // Retrieve activity data
        mTramLineLetter = requireArguments().getString(getString(R.string.TramLineLetterGen)).toString()
        (activity as AppCompatActivity).supportActionBar?.title = "${getString(R.string.line)} $mTramLineLetter"

        // View configurations
        val view = inflater.inflate(R.layout.fv_schedule, container, false)
        mRecyclerView = view?.findViewById(R.id.fv_schedule_RecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mRecyclerView?.adapter = ScheduleAdapter(ArrayList())

        // Handle button
        mButtonSchedule = view.findViewById(R.id.fv_schedule_buttonSeeMap)
        mButtonSchedule.setOnClickListener {
            val intent = Intent(activity, MapActivity::class.java)
            intent.putExtra(getString(R.string.TramLineLetterGen), mTramLineLetter)
            startActivity(intent)
        }
        return view
    }

    override fun onStart() {
        // onStart is called after onCreate view: it handles the logic part (network calls for us)

        super.onStart()
        val mLoadingScreen = LoadingScreen(context as Activity)
        val mErrorScreen = ErrorScreen(context as Activity)

        // Network calls
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, mTramLineLetter, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                mLoadingScreen.show()
            }

            override fun onSuccess(data: Line) {
                mLoadingScreen.dismiss()
                val scheduleList: ArrayList<ScheduleItem> = ArrayList()
                val length = data.stops?.size

                /* Need to check if length!=null if I want to use it in my for loop which needs
                   an Int and not Int?. A second approach is to use for each loop */
                if (length!= null ) {
                    for (i in 0..length) {
                        val sdf = SimpleDateFormat("HH:mm", Locale.FRANCE)
                        var tramStationName = data.stops?.get(i)?.name

                        if(tramStationName?.length != null)
                        {
                            if (tramStationName.length > 30) {
                                // If name too long, short it to see the correct time
                                tramStationName = tramStationName.substring(0, 27)
                                tramStationName += ".."
                            }
                        }

                        if (i == length - 1) {
                            // Add the terminus one, which has no "departure time"
                            tramStationName?.let { ScheduleItem("TERMINUS", it, mTramLineLetter) }?.let { scheduleList.add(it) }
                            // Add a fake one to see the last one
                            scheduleList.add(ScheduleItem("", "", ""))
                            break
                        }

                        val date = data.stops?.get(i)?.estimatedDepartureTime
                        if (date != null)
                        {
                            val nextDepartureTime = sdf.format(date).replace(':', 'h')
                            tramStationName?.let { ScheduleItem(nextDepartureTime, it, mTramLineLetter) }?.let { scheduleList.add(it) }
                        }
                    }

                    // UpdateView
                    (mRecyclerView?.adapter as ScheduleAdapter).setScheduleList(scheduleList)
                    (mRecyclerView?.adapter as ScheduleAdapter).notifyDataSetChanged()
                }
                else {mErrorScreen.show()}
            }

            override fun onError(throwable: Throwable) {
                mLoadingScreen.dismiss()
                mErrorScreen.show()
            }
        })
    }

    companion object {
        fun onInstance(tramLineLetter: String?, tramLineKey: String): ScheduleFragment {
            val fragment = ScheduleFragment()
            val args = Bundle()
            args.putString(tramLineKey, tramLineLetter)
            fragment.arguments = args
            return fragment
        }
    }
}
