package com.example.artur

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class DrawingView(context: Context, attributes: AttributeSet) : View(context, attributes) {

    // An inner class for custom path with two params as color and stroke size.
    internal inner class CustomPath(var color: Int, var brushThickness: Float) : Path()

    //TODO finish making the DrawingView
    //TODO make a commit, stating, that this class was done

    private var drawPath: CustomPath? = null
    private var canvasBitmap: Bitmap? = null
    private var drawPaint: Paint? = null
    private var canvasPaint: Paint? = null
    private var brushSize by Delegates.notNull<Float>()     //lateinit Float variable
    private var brushColor by Delegates.notNull<Int>()    //lateinit Int variable
    private var canvas: Canvas? = null    //Canvas class holds the draw calls to write the bitmap
    private val pathsList = ArrayList<CustomPath>()
    private val undoPathsList = ArrayList<CustomPath>()

    init {
        setUpDrawingView()
    }

    private fun setUpDrawingView() {
        setupBasicBrush()

        //Set up paths storage
        drawPath = CustomPath(brushColor, brushSize)

        //Set up everything needed for Paint class
        setUpPaintClassVariables()
    }

    private fun setUpPaintClassVariables(){
        drawPaint!!.color = brushColor
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.MITER
        drawPaint!!.strokeCap = Paint.Cap.ROUND
        canvasPaint = Paint(Paint.DITHER_FLAG)   //Paint flag that enables smoothing when dithering.
    }

    override fun onSizeChanged(currentWidth: Int, currentHeight: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldHeight)
        canvasBitmap = Bitmap.createBitmap(currentWidth, currentHeight, Bitmap.Config.ARGB_8888)
        canvas = Canvas(canvasBitmap!!)
    }

    private fun setupBasicBrush() {
        brushColor = Color.BLACK
        brushSize = 20.toFloat()
    }

}