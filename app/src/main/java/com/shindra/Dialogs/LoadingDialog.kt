package com.shindra.Dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.shindra.R


class LoadingDialog {

    private var Dialog : AlertDialog? = null

    public fun CreateDialog (context : Context)
    {
        val DialogBuilder= AlertDialog.Builder(context)
        val dialogView= LayoutInflater.from(context).inflate(R.layout.loading_dialog,null)
        DialogBuilder.setView(dialogView)
        DialogBuilder.setCancelable(false)
        Dialog = DialogBuilder.create()
    }

    public fun ShowDialog()
    {
        Dialog?.show()
    }

    public fun CloseDialog()
    {
        Dialog?.dismiss()
    }


}