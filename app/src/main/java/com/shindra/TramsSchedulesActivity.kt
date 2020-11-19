package com.shindra

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class TramsSchedulesActivity : AppCompatActivity() {
    private val logTag = "TramsSchedulesActivity"

    var associatedLinename : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trams_schedules)

        // Get the Intent that started this activity and extract the string
        associatedLinename = intent.getStringExtra(StartActivity.LINENAME_MESSAGE).toString()
        Log.i(logTag, "Created second activity with tramline : " + associatedLinename)

        //Fragment get started automatically

        //When everything has finished creating, we can start the request to the api
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentTramTimetable) as TimetableFragment
        fragment.requestTimetable(associatedLinename.toString())
    }
}