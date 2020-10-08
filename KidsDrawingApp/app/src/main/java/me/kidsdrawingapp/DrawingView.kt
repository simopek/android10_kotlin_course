package me.kidsdrawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var mDrawPath: CustomPath? = null
    private var mCanvasBitmap: Bitmap? = null
    private var mDrawPaint: Paint? = null
    private var mCanvasPaint: Paint? = null
    private var mBrushSize: Float = 0f
    private var color = Color.BLACK
    private var canvas: Canvas? = null
    private val mPaths = ArrayList<CustomPath>()

    init {
        setupDrawing()
    }

    private fun setupDrawing() {
        mDrawPaint = Paint()
        mDrawPath = CustomPath(color, mBrushSize)
        mDrawPaint!!.color = color
        mDrawPaint!!.style = Paint.Style.STROKE
        mDrawPaint!!.strokeJoin = Paint.Join.ROUND
        mDrawPaint!!.strokeCap = Paint.Cap.ROUND
        mCanvasPaint = Paint(Paint.DITHER_FLAG)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mCanvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(mCanvasBitmap!!)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(mCanvasBitmap!!, 0f, 0f, mCanvasPaint)

        for (path in mPaths) {
            mDrawPaint?.let {

                it.strokeWidth = path.brushThickness
                it.color = path.color
                canvas?.drawPath(path, it)    
            }
        }

        if (!mDrawPath!!.isEmpty) {
            // we can use brushThickness because mDrawPath is CustomPath
            mDrawPaint!!.strokeWidth = mDrawPath!!.brushThickness
            mDrawPaint!!.color = mDrawPath!!.color
            canvas!!.drawPath(mDrawPath!!, mDrawPaint!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event == null) {
            // if we null-check, kotlin use the smart cast
            return false
        }

        val touchX = event.x
        val touchY = event.y

        when (event.action) {
            // we press down
            MotionEvent.ACTION_DOWN -> {

                // "let" can be used to run
                // a lambda expression on the target object.
                // The elvis operator executes the method only
                // if the reference is non-null, so by using the elvis
                // operator and "let" we can avoid the null-check
                mDrawPath?.let {

                    it.color = color
                    it.brushThickness = mBrushSize

                    it.reset()
                    it.moveTo(touchX, touchY)
                }

            }
            // we keep pressing while moving
            MotionEvent.ACTION_MOVE -> {

                mDrawPath!!.lineTo(touchX, touchY)
            }
            // we stop touching
            MotionEvent.ACTION_UP -> {

                mPaths.add(mDrawPath!!)
                mDrawPath = CustomPath(color, mBrushSize)
            }
            // this way we say the event has not been handled
            else -> return false
        }

        // so we force a redraw of the view; the onDraw method will be called
        invalidate()

        return true
    }

    fun setBrushSize(newSize: Float) {

        // with TypedValue we take into account the dimension unit and we convert the size
        mBrushSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize,
            resources.displayMetrics)
        mDrawPaint!!.strokeWidth = mBrushSize
    }


    internal class CustomPath(
        var color: Int,
        var brushThickness: Float
    ) : Path() {

    }
}