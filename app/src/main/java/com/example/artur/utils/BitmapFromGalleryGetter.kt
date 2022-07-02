package com.example.artur.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.artur.activity.MainActivity
import com.example.artur.databinding.ActivityMainBinding
import java.net.URI

class BitmapFromGalleryGetter(
    componentActivity: ComponentActivity,
    private val binding: ActivityMainBinding) {

    fun getPhotoFromGallery() {
        startGalleryIntent()
    }

    private fun startGalleryIntent() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        galleryLauncher.launch(galleryIntent)

    }

    private val galleryLauncher =
        componentActivity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                changeBackgroundImage(data!!.data!!)
            }
        }

    private fun changeBackgroundImage(uri: Uri) {
        binding.ivBackground.visibility = View.VISIBLE
        binding.ivBackground.setImageURI(uri)
    }
}