package com.shindra.Time

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.LoadingClass
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import java.util.ArrayList

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_fragment_holder)
        title = "Horaire"
        val bundle = Bundle()
        bundle.putString("name", intent.getStringExtra("name"))
        val fragment = TimeFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.time_frameLayout,fragment).commit()
    }
}
