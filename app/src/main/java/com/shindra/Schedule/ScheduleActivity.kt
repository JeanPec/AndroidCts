package com.shindra.Schedule

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Lines.FragmentLine
import com.shindra.Map.MapActivity
import com.shindra.MapClick
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class ScheduleActivity : AppCompatActivity() {

    val stops = ArrayList<Stop>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val lineID = intent.getStringExtra("LINE")!!
        println("OUT $lineID")

        setContentView(R.layout.activity_schedule)
        title = "Ligne $lineID"

        val bundle = Bundle()
        bundle.putString("LINE_ID", lineID)
        val fragmentSchedule = FragmentSchedule()
        fragmentSchedule.arguments = bundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentSchedule, fragmentSchedule);
        fragmentTransaction.commit();
    }


}