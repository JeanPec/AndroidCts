package com.shindra.OurTrams

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Misc.ErrorScreen
import com.shindra.Misc.LoadingScreen
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.Schedule.ScheduleActivity
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import kotlin.collections.ArrayList

class OurTramsFragment : Fragment() {

    // Attributes
    private val TAG = "OurTramsFragment"
    private var mRecyclerView: RecyclerView? = null

    // Methods
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // onCreateView is called after onCreate: it retrieves the view

        Log.i(TAG, "onCreateView")

        // View configurations
        val view = inflater.inflate(R.layout.fv_our_trams, container, false)
        mRecyclerView = view.findViewById(R.id.fv_our_trams_RecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mRecyclerView?.adapter = OurTramsAdapter(ArrayList(), object : OurTramsAdapter.OnItemClickListener
        {
            override fun onButtonScheduleClick(tramLineName: String) {
                var intent = Intent(activity, ScheduleActivity::class.java)
                intent.putExtra("tramLineLetter", tramLineName)
                startActivity(intent)
            }
        })
        return view;
    }

    override fun onStart() {
        // onStart is called after onCreate view: it handles the logic part (network calls for us)

        Log.i(TAG, "onStart")
        super.onStart()

        val mLoadingScreen = LoadingScreen(context as Activity)
        val mErrorScreen = ErrorScreen(context as Activity)

        // Network calls
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                Log.i(TAG, "onLoading")
                mLoadingScreen.show()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                Log.i(TAG, "onSuccess")
                mLoadingScreen.dismiss()

                // Get only tram data and convert it to OurTramsItem
                var ourTramsList = ArrayList<OurTramsItem>();
                var dataFiltered = (data.filter { it.routeType == RouteType.TRAM })
                dataFiltered.forEach{
                    ourTramsList.add(OurTramsItem(it.name))
                }

                // Update view
                (mRecyclerView?.adapter as OurTramsAdapter).SetOurTramsList(ourTramsList)
            }

            override fun onError(throwable: Throwable) {
                Log.i(TAG, "onError")
                mLoadingScreen.dismiss()
                mErrorScreen.show()
            }
        })
    }
}
