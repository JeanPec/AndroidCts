package com.shindra

import Fragments.LineMapFragment
import Fragments.ScheduleFragment
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class LineMapActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_map_activity)
        setTitle(getString(R.string.line_name, intent.getStringExtra("lineTramName")))
        val lineMapFragment = LineMapFragment().apply { arguments = intent.extras}
        supportFragmentManager.beginTransaction().add(R.id.lineMapFragment, lineMapFragment).commit()
    }
}