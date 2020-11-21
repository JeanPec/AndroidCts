package com.shindra.schedules

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Activity configuration
        setContentView(R.layout.schedule_activity)
        setTitle(R.string.schedule_activity_name)

        //data recovery
        val lineName = intent.getStringExtra("lineName")

        //Fragment configuration
        val fragmentStop = lineName?.let { ScheduleFragmentStop().newInstance(it) }
        fragmentStop?.let { supportFragmentManager.beginTransaction().add(R.id.fragment_stop, it).commit() }
    }
}