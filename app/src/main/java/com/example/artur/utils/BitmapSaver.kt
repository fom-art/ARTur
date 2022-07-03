package com.example.artur.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.widget.Toast
import com.example.artur.DrawingView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class BitmapSaver(drawingView: DrawingView, private val context: Context) {
    private val bitmap = drawingView.returnCurrentBitmap()
    private var fileName = ""

    fun savePhotoToStorage() {
        val bytes = ByteArrayOutputStream()

        bitmap.compress(
            Bitmap.CompressFormat.PNG,
            100,
            bytes
        )

        val file = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString() + File.separator
                    + System.currentTimeMillis() / 1000
                    + ".png"
        )

        val fos = FileOutputStream(file)
        fos.write(bytes.toByteArray())
        fos.close()
        fileName = file.absolutePath
    }

    fun sendToast() {
        Toast.makeText(
            context,
            "The file $fileName was saved",
            Toast.LENGTH_LONG
        ).show()
    }
}