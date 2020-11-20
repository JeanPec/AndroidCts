package com.shindra

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog


class LoadingClass(context: Activity) : AlertDialog(context) {

    init{
        this.setView(context.layoutInflater.inflate(R.layout.loading_layout, listView))
    }

}