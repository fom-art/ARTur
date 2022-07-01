package com.example.artur

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
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

    private fun setUpPaintClassVariables() {
        drawPaint!!.color = brushColor
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.MITER
        drawPaint!!.strokeCap = Paint.Cap.ROUND
        canvasPaint = Paint(Paint.DITHER_FLAG)   //Paint flag that enables smoothing when dithering.
    }

    ///Function is initialized once the view is shown on the screen and
    //Initializes the bitmap and canvas
    override fun onSizeChanged(
        currentWidth: Int,
        currentHeight: Int,
        oldWidth: Int,
        oldHeight: Int
    ) {
        super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldHeight)
        canvasBitmap = Bitmap.createBitmap(currentWidth, currentHeight, Bitmap.Config.ARGB_8888)
        canvas = Canvas(canvasBitmap!!)
    }

    //Function is being called, when the stroke is drawn
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitmap!!, 0f, 0f, canvasPaint)

        for(path in pathsList) {
            drawPaint!!.strokeWidth = path.brushThickness
            drawPaint!!.color = path.color
            canvas.drawPath(path, drawPaint!!)
        }

        if(drawPath!!.isEmpty) {
            drawPaint!!.strokeWidth = drawPath!!.brushThickness
            drawPaint!!.color = drawPath!!.color
            canvas.drawPath(drawPath!!, drawPaint!!)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                beginTheLine(touchX, touchY)
            }
            MotionEvent.ACTION_MOVE -> {
                addLine(touchX, touchY)
            }
            MotionEvent.ACTION_UP -> {
                drawPath = CustomPath(brushColor, brushSize)
            }
            else -> return false
        }

        invalidate()    //Used to notify the ViewModel, that the data (paths) has changed
        return true
    }

    fun undoOneStep() {
        if (pathsList.size > 0) {
            undoPathsList.add(pathsList.removeAt(pathsList.size - 1))
            invalidate()    //Used to notify the ViewModel, that the data (paths) has changed
        }
    }

    private fun beginTheLine(cordX: Float, cordY: Float) {
        drawPath!!.color = brushColor
        drawPath!!.brushThickness = brushSize

        drawPath!!.reset()    //Clear a previous path from the path variable
        drawPath!!.moveTo(cordX, cordY)    //Set the beginning of the upcoming line
    }

    //Adds a line to the specified point
    private fun addLine(cordX: Float, cordY: Float) {
        drawPath!!.moveTo(cordX, cordY)
    }

    private fun setupBasicBrush() {
        brushColor = Color.BLACK
        brushSize = 20.toFloat()
    }
}