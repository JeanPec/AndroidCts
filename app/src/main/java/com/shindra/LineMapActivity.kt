package com.shindra

import Fragments.LineMapFragment
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class LineMapActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.line_map_activity)

        val lineTramName = intent.getStringExtra("lineTramName")

        val lineMapFragment = LineMapFragment()
        val bundle = Bundle()
        bundle.putString("lineTramName", lineTramName)
        lineMapFragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.lineMapFragment, lineMapFragment).commit()
    }
}