package com.farroos.penjualanapp.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class MapsHelper {

    companion object {
        fun permissionMap(context: Context, activity: Activity) {

            if (ActivityCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    com.farroos.penjualanapp.data.Constant.LOCATION_PERMISSION_REQUEST_CODE
                )

                return
            }
        }
    }
}