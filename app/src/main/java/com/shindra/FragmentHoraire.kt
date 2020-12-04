package com.shindra

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import kotlin.math.log

class FragmentHoraire : Fragment() {
    lateinit var recyclerView : RecyclerView
    private lateinit var viewFragment : View
    lateinit var nomTram: String
    private var listeArret = ArrayList<Stop>()

    companion object {
        @JvmStatic
        fun newInstance(nomTram: String): FragmentHoraire{
            val fragmentHoraire = FragmentHoraire()
            val args = Bundle()
            args.putString("nomTram", nomTram)
            fragmentHoraire.arguments = args
            return fragmentHoraire
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        nomTram = arguments?.getString("nomTram").toString()
        viewFragment = inflater.inflate(R.layout.fragment_horaire, container, false)
        recyclerView = viewFragment.findViewById(R.id.RecyclerViewHoraire)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = RecyclerHoraireAdapter(listeArret, nomTram)
        Log.i("nomdutram", nomTram)

        val ecranChargement = Chargement(requireActivity())

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
            model.lineWithEstimatedTimeTable(RouteType.TRAM, nomTram, 0).observe(object :
                ObservableListener<Line> {

            override fun onLoading() {
                ecranChargement.show()
            }
            override fun onSuccess(data: Line) {
                ecranChargement.dismiss()
                (recyclerView.adapter as RecyclerHoraireAdapter).listeArret = data.stops!!
                (recyclerView.adapter as RecyclerHoraireAdapter).notifyDataSetChanged()
                Log.i("nomdutram2", nomTram)
                Log.i("listetram", listeArret.toString())
            }
            override fun onError(throwable: Throwable) {
                ecranChargement.dismiss()
            }
        })

        return viewFragment
    }




}