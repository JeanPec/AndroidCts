package com.shindra

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class TramsSchedulesActivity : AppCompatActivity() {
    private val logTag = "TramsSchedulesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trams_schedules)

        // Get the Intent that started this activity and extract the string
        val requestedLineName = intent.getStringExtra(StartActivity.LINENAME_MESSAGE)
        Log.i(logTag, "Created second activity with tramline : " + requestedLineName)

        //Fragment get started automatically

        //When everything has finished creating, we can start the request to the api
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentTramTimetable) as TimetableFragment
        fragment.requestTimetable(requestedLineName.toString())
    }
}