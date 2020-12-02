package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class mapActivity : AppCompatActivity() {
    private var frag: mapFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        supportActionBar!!.title = getString(R.string.ligneText) + intent.getStringExtra("LINE_NAME")

        //Create a new fragment stationFragment and his bundle
        frag = mapFragment()
        val bundle = Bundle()

        //begin transaction between the container and the fragment frag. Add parameter to the bundle
        supportFragmentManager.beginTransaction().add(R.id.map_container, frag!!).commit()
        bundle.putString("lineName", intent.getStringExtra("LINE_NAME"))
        frag!!.arguments = bundle
    }
}