package com.shindra.Lines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shindra.*

class LinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line)
        title = getString(R.string.line_activity_title)

        val fragmentTram = FragmentLine()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentTram, fragmentTram)
        fragmentTransaction.commit()
    }

}