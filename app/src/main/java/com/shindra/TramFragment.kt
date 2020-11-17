package com.shindra

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.RecyclerItemClick as RecyclerItemClick1


/**
 * A simple [Fragment] subclass.
 * Use the [TramFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TramFragment : Fragment() {

    private lateinit var cardViewAdapter: CardViewAdapter
    val _tramList = arrayListOf<Tram>(Tram("tram_a", "nouveau_tram_strasbourg"),
            Tram("tram_b", "nouveau_tram_strasbourg"),
            Tram("tram_c", "nouveau_tram_strasbourg"),
            Tram("tram_d", "nouveau_tram_strasbourg"),
            Tram("tram_e", "nouveau_tram_strasbourg"),
            Tram("tram_f", "nouveau_tram_strasbourg"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
       var view : View = inflater.inflate(R.layout.fragment_tram, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerView:RecyclerView = view.findViewById(R.id.recyclerViewTram)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = CardViewAdapter(_tramList, object : com.shindra.RecyclerItemClick {
            override fun onScheduleClick(tram: Tram) {
                Toast.makeText(context, tram.line.toString(),Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun OnButtonClick(button : Button)
    {
        button.setBackgroundColor(Color.RED)

    }

}