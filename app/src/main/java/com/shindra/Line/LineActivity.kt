package com.shindra.Line

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

class LineActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line)

        val fragment = LineFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameContainer, fragment).commit()

        setTitle(R.string.lines)
    }
}