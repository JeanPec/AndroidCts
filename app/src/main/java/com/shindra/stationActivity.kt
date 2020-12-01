package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class stationActivity : AppCompatActivity() {
    private var frag: stationFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_diary)
        supportActionBar!!.title = getString(R.string.stationActivityName)

        //Create a new fragment stationFragment and his bundle
        frag = stationFragment()
        val bundle = Bundle()

        //begin transaction between the container and the fragment frag. Add parameter to the bundle
        supportFragmentManager.beginTransaction().add(R.id.container_station, frag!!).commit()
        bundle.putString("lineName", intent.getStringExtra("TRAM_LINE"))
        frag!!.arguments = bundle
    }
}