package com.shindra.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class ScheduleActivity : AppCompatActivity() {

    // Attributes
    private lateinit var tramLineKey: String
    private lateinit var mFragment: ScheduleFragment
    private var mTramLineLetter: String? = null

    // Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.av_schedule)
        tramLineKey = getString(R.string.TramLineLetterGen)

        // Retrieve intent data
        val intent = intent
        mTramLineLetter = intent?.getStringExtra(tramLineKey)

        // Create, pass args & call fragment
        mFragment = ScheduleFragment.onInstance(mTramLineLetter, tramLineKey)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.av_schedule_fragmentHolder, mFragment).commit()
    }
}