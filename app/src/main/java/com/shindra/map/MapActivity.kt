package com.shindra.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R
import com.shindra.time.TimeActivity

class MapActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_fragment_holder)
        title = getString(R.string.line)+(intent.getStringExtra("name"))

        val fragment = MapWithStopFragment.newInstance(intent.getStringExtra("name"))
        supportFragmentManager.beginTransaction().add(R.id.map_frameLayout,fragment).commit()
    }

    /*companion object{
        fun newInstance(context: Context,line_name : String? ) : Intent {
            val intent = Intent(context, MapActivity.javaClass)
            intent.putExtra("name", line_name)
            return intent
        }
    }*/
}