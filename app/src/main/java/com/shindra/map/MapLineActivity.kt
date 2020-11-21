package com.shindra.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class MapLineActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //Activity configuration
        setContentView(R.layout.map_line_activity)

        //data recovery
        val lineName = intent.getStringExtra("lineName")
        title = lineName?.let { getLineName(it) }

        //Fragment configuration
        val fragment = lineName?.let { MapLineFragment().newInstance(it) }
        fragment?.let { supportFragmentManager.beginTransaction().add(R.id.fragment_line_map, it).commit() }
    }

    private fun getLineName(line: String) : String {
        return when (line){
            getString(R.string.line_a) -> getString(R.string.line_a_display)
            getString(R.string.line_b) -> getString(R.string.line_b_display)
            getString(R.string.line_c) -> getString(R.string.line_c_display)
            getString(R.string.line_d) -> getString(R.string.line_d_display)
            getString(R.string.line_e) -> getString(R.string.line_e_display)
            getString(R.string.line_f) -> getString(R.string.line_f_display)
            else -> getString(R.string.line_error_display)
        }
    }
}