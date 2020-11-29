package com.shindra.Schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val lineID = intent.getStringExtra(getString(R.string.line_to_schedule_activity_intent_name))

        setContentView(R.layout.activity_schedule)
        title = getString(R.string.schedule_activity_title)

        val bundle = Bundle()
        bundle.putString(getString(R.string.schedule_activity_to_fragment_bundle_name), lineID)
        val fragmentSchedule = FragmentSchedule()
        fragmentSchedule.arguments = bundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentSchedule, fragmentSchedule)
        fragmentTransaction.commit()
    }


}