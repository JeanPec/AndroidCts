package com.shindra.time

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.utilis.LoadingClass
import com.shindra.map.MapActivity
import com.shindra.utilis.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class TimeFragment : Fragment() {

    fun newInstance(): TimeFragment {
        return TimeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view= inflater.inflate(R.layout.time_fragment_stop, container, false)
        val stops_recylcer_view = view.findViewById<RecyclerView>(R.id.stop_container)
        stops_recylcer_view.layoutManager = LinearLayoutManager(activity)

        val line_name = this.arguments?.getString("name")

        val dialog = LoadingClass(activity as Activity)
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, line_name!!,0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                dialog.show()
            }

            override fun onSuccess(data: Line) {
                dialog.dismiss()
                //call once the network call has responded with a success
                stops_recylcer_view.adapter = TimeAdapter(data.stops!!, line_name )
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         view.findViewById<Button>(R.id.button_map).setOnClickListener{
             //val intent = MapActivity.newInstance(activity as Activity,this.arguments?.getString("name"))//Intent(context, MapActivity::class.java)
             val intent = Intent(activity, MapActivity :: class.java)
             intent.putExtra("name", this.arguments?.getString("name"))
             startActivity(intent)
         }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object{
        /**
         * Create a new instance of DetailsFragment, initialized to
         * show the text at 'index'.
         */
        fun newInstance(name_line: String?): TimeFragment {
            val f = TimeFragment()

            // Supply index input as an argument.
            val args = Bundle()
            args.putString("name", name_line)
            f.arguments = args

            return f
        }
    }

}
