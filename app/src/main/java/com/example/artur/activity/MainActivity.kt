package com.example.artur.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.artur.R
import com.example.artur.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var currentColorImageButtonSelected: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setBasicBrushSettings()
    }

    private fun setBasicBrushSettings() {
        //Set the first element of llPaintColors as the basic one
        val basicColorImageButton = binding.llPaintColors[0] as ImageButton

        //Make a selection of the basic color
        changePaintClicked(basicColorImageButton)

        currentColorImageButtonSelected = basicColorImageButton
    }

    fun changePaintClicked(view: View) {
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
}