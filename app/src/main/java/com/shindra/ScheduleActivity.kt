package com.shindra

import Fragments.ScheduleFragment
import android.os.Bundle
import android.util.Log
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
        setTitle(R.string.tram_schedule)

        val scheduleFragment = ScheduleFragment().apply { arguments = intent.extras}
        supportFragmentManager.beginTransaction().add(R.id.scheduleFragment, scheduleFragment).commit()
    }
}
