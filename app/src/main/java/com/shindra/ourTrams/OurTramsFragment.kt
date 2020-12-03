package com.shindra.ourTrams

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ErrorScreen
import com.shindra.LoadingScreen
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.schedule.ScheduleActivity
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import kotlin.collections.ArrayList

class OurTramsFragment : Fragment() {

    // Attributes
    private lateinit var mRecyclerView: RecyclerView

    // Methods
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // onCreateView is called after onCreate: it retrieves the view

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.OurTrams)

        // View configurations
        val view = inflater.inflate(R.layout.fv_our_trams, container, false)
        mRecyclerView = view.findViewById(R.id.fv_our_trams_RecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = OurTramsAdapter(ArrayList(), object : OurTramsAdapter.OnItemClickListener
        {
            override fun onButtonScheduleClick(tramLineName: String) {
                val intent = Intent(activity, ScheduleActivity::class.java)
                intent.putExtra(getString(R.string.TramLineLetterGen), tramLineName)
                startActivity(intent)
            }
        })
        return view
    }

    override fun onStart() {
        // onStart is called after onCreate view: it handles the logic part (network calls for us)

        super.onStart()

        val mLoadingScreen = LoadingScreen(context as Activity)
        val mErrorScreen = ErrorScreen(context as Activity)

        // Network calls
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                mLoadingScreen.show()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                mLoadingScreen.dismiss()

                // Get only tram data and convert it to OurTramsItem
                val ourTramsList = ArrayList<OurTramsItem>()
                val dataFiltered = (data.filter { it.routeType == RouteType.TRAM })
                dataFiltered.forEach{
                    ourTramsList.add(OurTramsItem(it.name))
                }

                // Update view
                (mRecyclerView.adapter as OurTramsAdapter).setOurTramsList(ourTramsList)
                (mRecyclerView.adapter as OurTramsAdapter).notifyDataSetChanged()
            }

            override fun onError(throwable: Throwable) {
                mLoadingScreen.dismiss()
                mErrorScreen.show()
            }
        })
    }
}
