package com.example.artur.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import java.util.jar.Manifest
import kotlin.properties.Delegates

class Permissions(
    private val context: Context,
    private val activity: Activity
) {


    fun checkPermissions(): Boolean {
        return if (arePermissionsGranted()) {
            true
        } else {
            requestPermissions()
            false
        }
    }

    private fun arePermissionsGranted(): Boolean {
        val isReadPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val isWritePermissionGranted = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return (isReadPermissionGranted == PackageManager.PERMISSION_GRANTED
                &&
                isWritePermissionGranted == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE

            ),
            Constants.READ_AND_WRITE_PERMISSIONS_GRANTED
        )
    }
}