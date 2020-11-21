package com.shindra

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shindra.Schedule__Fragment.Companion.LINENAME_MESSAGE

class MapActivity : AppCompatActivity() {
    private val logTag = "GpsMapActivity"
    companion object{
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)

        val requestedLineName = intent.getStringExtra(Schedule__Fragment.LINENAME_MESSAGE)


        supportActionBar?.title = getString(R.string.Schedule_LineName, requestedLineName)

        //start fragment
        val args = Bundle()
        val fragmentManager = supportFragmentManager
        val fragment = MapFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        args.putString(LINENAME_MESSAGE, requestedLineName)
        fragment.arguments = args

        fragmentTransaction.add(R.id.stopfragment, fragment)
        fragmentTransaction.commit()

    }
}