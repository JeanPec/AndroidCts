package com.shindra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shindra.arrakis.controls.MapFragment

class StopsMapActivity : AppCompatActivity() {
    private val logTag = "GpsMapActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stops_map)

        // Get the Intent that started this activity and extract the string
        val requestedLineName = intent.getStringExtra(TimetableFragment.LINENAME_MESSAGE)
        Log.i(logTag, "Created gps activity with tramline : " + requestedLineName)

        supportActionBar!!.title = getString(R.string.cvTimetable_LineNamePrefix, requestedLineName)

        //start fragment
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = StopsMapFragment()
        fragmentTransaction.add(R.id.stopsMap_fragment, fragment)
        fragmentTransaction.commit()

    }
}