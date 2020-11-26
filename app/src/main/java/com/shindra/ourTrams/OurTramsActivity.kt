package com.shindra.ourTrams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class OurTramsActivity : AppCompatActivity() {

    // Attributes
    private lateinit var mFragment: OurTramsFragment

    // Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.av_our_trams)

        // Create & call our fragment
        mFragment = OurTramsFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.av_our_trams_fragmentHolder, mFragment).commit()
    }
}