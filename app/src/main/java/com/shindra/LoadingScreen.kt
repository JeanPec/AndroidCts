package com.shindra.Misc

import android.app.Activity
import android.app.AlertDialog
import com.shindra.R

class LoadingScreen(context: Activity) : AlertDialog(context)
{
    init
    {
        this.setView(context.layoutInflater.inflate(R.layout.loading_screen, listView))
    }
}
