package emg.example.kda.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class DrawingView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {
    private var mDrawPath: CustomPath? = null
    private var mCanvasBitmap: Bitmap? = null
    private var mDrawPaint: Paint? = null
    private var mCanvasPaint: Paint? = null
    private var mBrushSize: Float = 0.0f
    private var color = Color.BLACK
    private var canvas: Canvas? = null

    init {
        setUpDrawing()
    }

    private fun setUpDrawing() {
        mDrawPaint = Paint()
        mDrawPath = CustomPath(color, mBrushSize)
        mDrawPaint?.let {
            it.color = color
            it.style = Paint.Style.STROKE
            it.strokeJoin = Paint.Join.ROUND
            it.strokeCap = Paint.Cap.ROUND
        }
        mCanvasPaint = Paint(Paint.DITHER_FLAG)
        mBrushSize = 20.0f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCanvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        mCanvasBitmap?.let {
            canvas = Canvas(it)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mCanvasBitmap?.let {
            canvas?.drawBitmap(it, 0f, 0f, mCanvasPaint)
        }
        mDrawPath?.let { path ->
            if (!path.isEmpty) {
                mDrawPaint?.let { paint ->
                    paint.strokeWidth = path.brushThickness
                    paint.color = path.color
                    canvas?.drawPath(path, paint)
                }
            }

        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event?.x
        val touchY = event?.y

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mDrawPath?.let {
                    it.color = color
                    it.brushThickness = mBrushSize
                    it.reset()
                    if (touchX != null && touchY != null) {
                        it.moveTo(touchX, touchY)
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                mDrawPath?.let {
                    if (touchX != null && touchY != null) {
                        it.lineTo(touchX, touchY)
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                mDrawPath = CustomPath(color, mBrushSize)
            }
            else -> return false
        }
        invalidate()
        return true
    }


    internal inner class CustomPath(
        var color: Int,
        var brushThickness: Float
    ) : Path() {

    }
}