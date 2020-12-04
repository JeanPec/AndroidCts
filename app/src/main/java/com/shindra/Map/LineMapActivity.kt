package com.shindra.Map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.shindra.R
import com.shindra.Schedule.ScheduleFragment

class LineMapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_map)

        val intent = intent
        val line = intent.getStringExtra("line")
        setTitle("Ligne " + line)
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.lineMapFrameLayout, LineMapFragment.newInstance(line)).commit()
    }
}