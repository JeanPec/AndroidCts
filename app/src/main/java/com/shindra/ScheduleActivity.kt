package com.shindra

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SchedulesActivity : AppCompatActivity() {
    private val logTag = "TramsSchedulesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        val fragment = supportFragmentManager.findFragmentById(R.id.stopfragment) as StopFragment

    }
}