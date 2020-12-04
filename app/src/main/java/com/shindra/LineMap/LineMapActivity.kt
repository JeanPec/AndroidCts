package com.shindra.LineMap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class LineMapActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_map_activity)
        setTitle(getString(R.string.line_name, intent.getStringExtra("lineTramName")))

        // Initializing & passing the intent to the fragment
        val lineMapFragment = LineMapFragment().apply { arguments = intent.extras}
        supportFragmentManager.beginTransaction().add(R.id.lineMapFragment, lineMapFragment).commit()
    }
}