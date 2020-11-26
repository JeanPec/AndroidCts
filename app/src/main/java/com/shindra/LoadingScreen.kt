package com.shindra

import android.app.Activity
import android.app.AlertDialog

class LoadingScreen(context: Activity) : AlertDialog(context)
{
    init
    {
        this.setView(context.layoutInflater.inflate(R.layout.loading_screen, listView))
    }
}
