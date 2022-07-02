package com.example.artur.activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.artur.DrawingView
import com.example.artur.R
import com.example.artur.databinding.ActivityMainBinding
import com.example.artur.utils.Constants
import com.example.artur.utils.Permissions

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var currentColorImageButtonSelected: ImageButton? = null

    //TODO finish MainActivity functionality

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setBasicBrushSettings()

        binding.ibBrush.setOnClickListener(this)
        binding.ibGallery.setOnClickListener(this)
        binding.ibSave.setOnClickListener(this)
        binding.ibUndo.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.ib_brush -> {
                showChangeBrushSizeDialog()
            }
            R.id.ib_gallery -> {
                initiateChoosingPhotoFromGallery()
            }
            R.id.ib_undo -> {
                binding.drawingView.undoOneStep()
            }
            R.id.ib_save -> {
                saveBitmapToStorage()
            }
        }
    }

    private fun showChangeBrushSizeDialog() {
        //TODO
    }

    private fun initiateChoosingPhotoFromGallery() {
        if (Permissions(this, this).checkPermissions()) {
            startGalleryIntent()
        }
    }

    private fun saveBitmapToStorage() {
        if (Permissions(this, this).checkPermissions()) {

        }
    }

    private fun setBasicBrushSettings() {
        //Set the first element of llPaintColors as the basic one
        val basicColorImageButton = binding.llPaintColors[0] as ImageButton

        //Make a selection of the basic color
        changeClickedPaintBorder(basicColorImageButton)

        currentColorImageButtonSelected = basicColorImageButton
    }

    fun changeClickedPaintBorder(view: View) {
        if (view !== currentColorImageButtonSelected) {
            val imageButton = view as ImageButton

            setColorButtonClicked(imageButton)
            currentColorImageButtonSelected?.let { setColorButtonUnClicked(it) }

            currentColorImageButtonSelected = view
        }
    }

    private fun setColorButtonClicked(imageButton: ImageButton) {
        imageButton.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
        )
    }

    private fun setColorButtonUnClicked(imageButton: ImageButton) {
        imageButton.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_normal)
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_AND_WRITE_PERMISSIONS_GRANTED) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission was granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Oops! You have just declined the permission! \n" +
                            "To allow it now, you need to allow the permission in settings of your phone...",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}