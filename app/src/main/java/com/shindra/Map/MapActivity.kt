package com.shindra.Map

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.Schedule.FragmentSchedule
import com.shindra.Schedule.ScheduleRecyclerViewAdapter
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.Coordinate
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.sql.DriverManager
import java.util.ArrayList


class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val intent = intent
        val lineID = intent.getStringExtra("LINE_ID")
        println("OUT $lineID")
        title = "Ligne $lineID"

        val bundle = Bundle()
        bundle.putString("LINE_ID", lineID)
        val fragmentMap = FragmentMap()
        fragmentMap.arguments = bundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentMap, fragmentMap)
        fragmentTransaction.commit()

    }
}
