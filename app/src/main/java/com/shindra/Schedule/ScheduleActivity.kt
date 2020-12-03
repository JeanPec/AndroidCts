package com.shindra.Schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class ScheduleActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setTitle(R.string.tram_schedule)

        val scheduleFragment = ScheduleFragment().apply { arguments = intent.extras}
        supportFragmentManager.beginTransaction().add(R.id.scheduleFragment, scheduleFragment).commit()
    }
}
