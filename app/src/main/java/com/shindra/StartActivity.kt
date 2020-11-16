package com.shindra



//import com.shindra.MyViewModel.lines
import android.content.Intent
import com.shindra.arrakis.observable.observe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.shindra.R
import com.shindra.MyViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.ctslibrary.bo.Line
import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.apibo.RouteType

class StartActivity : AppCompatActivity(),TrainAdapter.TrainViewHolder.RecyclerItemClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Nos trams"
        val listLine = initList()

        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.adapter = TrainAdapter(listLine,this)
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

    override fun onItemClick(line: Line) {
        val intent = Intent(this,TimeActivity::class.java)
        intent.putExtra("name",line.name)
        startActivity(intent)
    }

}

private fun initList(): List<Line>{

    val list = ArrayList<Line>()
    list += Line("tram_a",RouteType.TRAM,null)
    list += Line("tram_b",RouteType.TRAM,null)
    return list

}