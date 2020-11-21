package com.shindra.Misc

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import com.shindra.R
import java.security.AccessControlContext

class ErrorDialog(context: Activity) : AlertDialog(context)
{
    init
    {
        this.setView(context.layoutInflater.inflate(R.layout.dialog_error, listView))
    }
}