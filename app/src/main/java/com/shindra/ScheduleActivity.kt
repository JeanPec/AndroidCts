package com.shindra

import Fragments.ScheduleFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.ctslibrary.apibo.RouteType

class ScheduleActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        val lineTramName = intent.getStringExtra("lineTramName")
        // https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        val bundle = Bundle()
        bundle.putString("lineTramName", lineTramName)

        val scheduleFragment = ScheduleFragment()
        scheduleFragment.arguments = bundle
    }
}
