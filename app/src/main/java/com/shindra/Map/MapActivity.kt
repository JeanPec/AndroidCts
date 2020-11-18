package com.shindra.Map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R
import com.shindra.arrakis.controls.MapFragment

class MapActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_fragment_holder)
        title = intent.getStringExtra("name")
        supportFragmentManager.beginTransaction().add(R.id.map_frameLayout,MapWithStopFragment()).commit()
    }
}