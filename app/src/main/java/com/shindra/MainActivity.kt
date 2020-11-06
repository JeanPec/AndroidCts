package com.shindra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.subscribeWithLifecycle
import com.shindra.ctslibrary.bo.EstimatedTimeTable
import com.shindra.ctslibrary.bo.Lines
import com.shindra.ctslibrary.bo.RouteType
import com.shindra.ctslibrary.bo.VeloParcs

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.estimatedTimeTable(RouteType.TRAM,"A",1).subscribeWithLifecycle(lifecycle, object : ObservableListener<EstimatedTimeTable> {
                override fun onLoading() {}

                override fun onSuccess(data: EstimatedTimeTable) {
                    Log.d("Observable","onSuccess")
                }

                override fun onError(throwable: Throwable) {
                    Log.d("Observable","onError ${throwable.message}")
                }

            })
        }

        Log.d("Observable","on create")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Observable","on start")
    }

}