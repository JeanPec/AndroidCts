package com.shindra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragementA : FragementA {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstabceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmenta, container)
    }

    override fun onViewCreated(view: View, savedInstabceState: Bundle?) {
        super.onViewCreated(view, savedInstabceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {

        }
    }
}