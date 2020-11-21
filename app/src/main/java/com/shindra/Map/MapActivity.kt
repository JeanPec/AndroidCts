package com.shindra.Map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shindra.R

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val SelectedTramLine: String? = intent.extras?.getString("Ligne")
        setTitle(getString(R.string.map_header) + " " + SelectedTramLine)

        //Appeler et afficher le fragment de la carte
        var _MapFragment = MapFragment(this, SelectedTramLine)
        supportFragmentManager.beginTransaction()
            .replace(R.id.Map_FrameLayout, _MapFragment)
            .commitAllowingStateLoss()
        }

}


