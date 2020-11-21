package com.shindra.Map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class MapActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val lineName = intent.getStringExtra(getString(R.string.lineNameKey))

        val fragment = MapLineFragment.newInstance(lineName!!)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameContainer, fragment).commit()

        setTitle(getString(R.string.lineText) + lineName)
    }
}