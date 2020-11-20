package com.shindra

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class StopsMapActivity : AppCompatActivity() {
    private val logTag = "GpsMapActivity"
    companion object{
        const val LINENAME_MESSAGE = "com.souf.ctsfip3a.LINENAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stops_map)

        // Get the Intent that started this activity and extract the string
        val requestedLineName = intent.getStringExtra(TimetableFragment.LINENAME_MESSAGE)
        Log.i(logTag, "Created gps activity with tramline : " + requestedLineName)

        //Change title of activity
        supportActionBar?.title = getString(R.string.cvTimetable_LineNamePrefix, requestedLineName)

        //start fragment
        val args = Bundle()
        val fragmentManager = supportFragmentManager
        val fragment = StopsMapFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        args.putString(LINENAME_MESSAGE, requestedLineName)
        fragment.arguments = args

        fragmentTransaction.add(R.id.stopsMap_fragment, fragment)
        fragmentTransaction.commit()

    }
}