package com.shindra.Map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R


class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val intent = intent
        val lineID = intent.getStringExtra(getString(R.string.schedule_to_map_activity_intent_name))
        title = getString(R.string.map_activity_title) + lineID

        val bundle = Bundle()
        bundle.putString(getString(R.string.map_activity_to_fragment_bundle_name), lineID)
        val fragmentMap = FragmentMap()
        fragmentMap.arguments = bundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentMap, fragmentMap)
        fragmentTransaction.commit()

    }
}
