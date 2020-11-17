package com.shindra.Map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R
import java.sql.DriverManager

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val intent = intent
        val lineName = intent.getStringExtra("LINE_ID")
        println("OUT $lineName")
    }
}