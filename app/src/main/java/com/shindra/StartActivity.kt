package com.shindra

import androidx.recyclerview.widget.RecyclerView


//import com.shindra.MyViewModel.lines
import com.shindra.arrakis.observable.observe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shindra.R
import com.shindra.MyViewModel
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.ctslibrary.bo.Line
import java.util.ArrayList

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<Train>()
        list +=Train(R.drawable.tram_a)
        list +=Train(R.drawable.tram_b)

        //recycler_view = findViewById((R.id.recycler_view))

        TrainAdapter(list)
        //activi

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