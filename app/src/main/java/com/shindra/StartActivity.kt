package com.shindra



//import com.shindra.MyViewModel.lines
import com.shindra.arrakis.observable.observe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shindra.R
import com.shindra.MyViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.ctslibrary.bo.Line
import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listTrain = initList()

        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.adapter = TrainAdapter(listTrain)
        recyclerview.layoutManager = LinearLayoutManager(this )
        recyclerview.setHasFixedSize(true)

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

private fun initList(): List<Train>{

    val list = ArrayList<Train>()
    list +=Train(R.drawable.tram_a)
    list +=Train(R.drawable.tram_b)
    list +=Train(R.drawable.tram_c)
    list +=Train(R.drawable.tram_d)
    list +=Train(R.drawable.tram_e)
    list +=Train(R.drawable.tram_f)
    return list

}