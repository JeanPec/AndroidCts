package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.bo.Line
import java.util.*

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setTitle(R.string.schedule_activity_name)

        val lineName = intent.getStringExtra("lineName")

        val bundle = Bundle()
        bundle.putString("lineName",lineName)

        val fragmentStop = ScheduleFragmentStop()
        fragmentStop.arguments=bundle

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_stop,fragmentStop).commit()

        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        model.lines().observe(object : ObservableListener<ArrayList<Line>> {

            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }
}