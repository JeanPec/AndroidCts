package com.shindra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shindra.ctslibrary.bo.Line

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val intent = intent
        val line = intent.getStringExtra("line")
        Toast.makeText(this,line,Toast.LENGTH_SHORT)
    }
}