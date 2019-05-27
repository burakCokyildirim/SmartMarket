package com.yazlab.smartmarket

import android.app.AlertDialog
import android.content.Context

object Utils {

    fun showAlertDialog(context: Context, content: String, completion: ()-> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Dikkat")
        builder.setMessage(content)
        builder.setPositiveButton("OK") { _, _ ->
            completion()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}