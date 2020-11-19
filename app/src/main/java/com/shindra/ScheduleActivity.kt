package com.shindra

import Fragments.ScheduleFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class ScheduleActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)

        val lineTramName = intent.getStringExtra("lineTramName")

        // https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        val bundle = Bundle()
        bundle.putString("lineTramName", lineTramName)
        val scheduleFragment = ScheduleFragment()
        scheduleFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.scheduleFragment, scheduleFragment).commit()

    }
}
