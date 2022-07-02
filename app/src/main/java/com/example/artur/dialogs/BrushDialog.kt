package com.example.artur.dialogs

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.ImageButton
import com.example.artur.R
import com.example.artur.databinding.ActivityMainBinding
import com.example.artur.databinding.DialogBrushSizeBinding

class BrushDialog(private val context: Context, private val binding: ActivityMainBinding) {

        private val brushDialog = Dialog(context)

    fun createBrushDialog() {
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setCancelable(true)

        setSmallButton()
        setMediumButton()
        setLargeButton()

        brushDialog.show()
    }

    private fun setSmallButton() {
        val smallBtn = brushDialog.findViewById<ImageButton>(R.id.ib_small_brush)
        smallBtn.setOnClickListener {
            binding.drawingView.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
    }

    private fun setMediumButton() {
        val mediumBtn = brushDialog.findViewById<ImageButton>(R.id.ib_medium_brush)
        mediumBtn.setOnClickListener {
            binding.drawingView.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
    }

    private fun setLargeButton() {
        val largeBtn = brushDialog.findViewById<ImageButton>(R.id.ib_large_brush)
        largeBtn.setOnClickListener {
            binding.drawingView.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
    }
}