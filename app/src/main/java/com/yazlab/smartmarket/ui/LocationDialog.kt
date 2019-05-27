package com.yazlab.smartmarket.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.location.Location
import android.os.Bundle
import com.yazlab.smartmarket.R
import kotlinx.android.synthetic.main.location_dialog.*

class LocationDialog(context: Context, cancelable:Boolean, cancelListener: DialogInterface.OnCancelListener,val locationDialogInterface: LocationDialogInterface):AlertDialog(context, cancelable, cancelListener) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_dialog)

        buttonAccept.setOnClickListener {
            val longitude = editTextLongitude.text.toString()
            val latitude = editTextLatitude.text.toString()

            if (longitude.isNotEmpty() and latitude.isNotEmpty()){
                dismiss()
                val loc1 = Location("")
                loc1.latitude = latitude.toDouble()
                loc1.longitude =  longitude.toDouble()
                locationDialogInterface.getLocation(loc1)
            }
        }
    }
}

interface LocationDialogInterface{
    fun getLocation(location: Location)
}