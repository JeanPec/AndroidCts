package com.shindra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class horaires_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horaires)

        val SelectedTramLine: String? = intent.extras?.getString("Ligne")
        setTitle("Horaires - Ligne " + SelectedTramLine)
    }
}