package ru.egprojects.androidlab.core.services

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

class LocationService {
    companion object {
        fun getLocation(activity: Activity): Task<Location> {
            if (!hasPermission(activity)) requestPermission(activity)

            return LocationServices
                .getFusedLocationProviderClient(activity)
                .lastLocation
        }

        private fun hasPermission(context: Context) = ContextCompat
            .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED

        private fun requestPermission(activity: Activity) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
        }
    }
}