package com.shindra.Map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.shindra.R
import java.sql.DriverManager


class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val fragmentMap = FragmentMap()

        val fragmentManager:FragmentManager = supportFragmentManager
        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentMap, fragmentMap);
        fragmentTransaction.commit();


        val intent = intent
        val lineID = intent.getStringExtra("LINE_ID")
        println("OUT $lineID")
        title = "Ligne $lineID"
    }
}
