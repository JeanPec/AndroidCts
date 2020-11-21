package com.shindra.time

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_fragment_holder)
        title = getString(R.string.time)

        val fragment = TimeFragment.newInstance(intent.getStringExtra("name"))
        supportFragmentManager.beginTransaction().add(R.id.time_frameLayout,fragment).commit()
    }

    /*companion object{
        fun newInstance(context: Context,line_name : String ) : Intent{
            val intent = Intent(context, TimeActivity.javaClass)
            intent.putExtra("name", line_name)
            return intent
        }
    }*/
}
