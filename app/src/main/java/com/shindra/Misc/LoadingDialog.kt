package com.shindra.Misc

import android.app.Activity
import android.app.AlertDialog
import com.shindra.R
import java.security.AccessControlContext

class LoadingDialog(context: Activity) : AlertDialog(context)
{
    init
    {
        this.setView(context.layoutInflater.inflate(R.layout.dialog_loading, listView))
    }
}