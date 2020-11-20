package com.shindra.Map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class MapActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_fragment_holder)
        title = "Ligne "+(intent.getStringExtra("name"))
        val bundle = Bundle()
        bundle.putString("name", intent.getStringExtra("name"))
        val fragment = MapWithStopFragment()
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().add(R.id.map_frameLayout,fragment).commit()
    }
}