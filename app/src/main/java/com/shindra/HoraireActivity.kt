package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class HoraireActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horaire)

        val fragmentHoraire = FragmentHoraire().apply { arguments = intent.extras}
        supportFragmentManager.beginTransaction().add(R.id.HoraireFragment, fragmentHoraire).commit()
    }
}