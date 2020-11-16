package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        title = "Horaire"
        val bundle = Bundle()
        bundle.putString("name",intent.getStringExtra("name"))
        val fragment = TimeFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit()

    }
}