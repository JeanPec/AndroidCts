package com.shindra.Map

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class MapActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val lineName = intent.getStringExtra("lineName")

        val fragment = MapLineFragment.newInstance(lineName!!)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameContainer, fragment).commit()

        setTitle("Ligne " + lineName)
    }
}