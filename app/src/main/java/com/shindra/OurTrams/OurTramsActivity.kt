package com.shindra.OurTrams

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class OurTramsActivity : AppCompatActivity() {

    // Attributes
    private lateinit var mFragment: OurTramsFragment
    private val TAG = "OurTramsActivity"

    // Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.av_our_trams)

        // Create & call our fragment
        mFragment = OurTramsFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.av_our_trams_fragmentHolder, mFragment).commit()
    }
}