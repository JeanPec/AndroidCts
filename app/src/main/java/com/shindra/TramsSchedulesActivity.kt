package com.shindra

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class TramsSchedulesActivity : AppCompatActivity() {
    private val TAG = "TramsSchedulesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trams_schedules)

        // Get the Intent that started this activity and extract the string
        val requestedLineName = intent.getStringExtra(StartActivity.LINENAME_MESSAGE)

        Log.i(TAG, "Created second activity with tramline : " + requestedLineName)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentTramTimetable) as TimetableFragment
        fragment.RequestTimetable(requestedLineName.toString())
    }
}