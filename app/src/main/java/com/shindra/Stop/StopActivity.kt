package com.shindra.Stop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class StopActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop)

        val lineName = intent.getStringExtra("lineName")

        val fragment = StopFragment.newInstance(lineName)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameContainer, fragment).commit()

        setTitle("Ligne " + lineName)
    }
}