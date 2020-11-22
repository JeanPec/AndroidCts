package com.shindra.Schedule

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class ScheduleActivity : AppCompatActivity() {

    // Attributes
    private val TAG = "ScheduleActivity"
    private lateinit var mFragment: ScheduleFragment
    private lateinit var mTramLineLetter: String

    // Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.av_schedule)

        // Retrieve intent data
        val intent = intent
        mTramLineLetter = (intent.getStringExtra("tramLineLetter"))!!

        // Create, pass args & call fragment
        mFragment = ScheduleFragment.onInstance(mTramLineLetter)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.av_schedule_fragmentHolder, mFragment).commit()
    }
}