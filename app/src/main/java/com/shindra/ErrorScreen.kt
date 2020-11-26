package com.shindra

import android.app.Activity
import android.app.AlertDialog

class ErrorScreen(context: Activity) : AlertDialog(context)
{
    init
    {
        this.setView(context.layoutInflater.inflate(R.layout.error_screen, listView))
    }
}
