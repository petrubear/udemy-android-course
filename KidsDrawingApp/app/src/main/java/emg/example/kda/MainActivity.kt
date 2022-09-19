package emg.example.kda

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import emg.example.kda.views.DrawingView

class MainActivity : AppCompatActivity() {
    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentPaint: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.0f)

        val linearLayout = findViewById<LinearLayout>(R.id.ll_colors)
        mImageButtonCurrentPaint = linearLayout[1] as ImageButton
        mImageButtonCurrentPaint?.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_selected)
        )

        val ibBrush = findViewById<ImageButton>(R.id.ib_brush)
        ibBrush.setOnClickListener {
            showSizePickerDialog()
        }
    }

    private fun showSizePickerDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size: ")
        val smallBtn = brushDialog.findViewById(R.id.ib_small_brush) as ImageButton
        smallBtn.setOnClickListener {
            drawingView?.setSizeForBrush(10.0f)
            brushDialog.dismiss()
        }
        val mediumBtn = brushDialog.findViewById(R.id.ib_medium_brush) as ImageButton
        mediumBtn.setOnClickListener {
            drawingView?.setSizeForBrush(20.0f)
            brushDialog.dismiss()
        }
        val largeBtn = brushDialog.findViewById(R.id.ib_large_brush) as ImageButton
        largeBtn.setOnClickListener {
            drawingView?.setSizeForBrush(30.0f)
            brushDialog.dismiss()
        }
        brushDialog.show()
    }

    fun paintClicked(view: View) {
        if (view !== mImageButtonCurrentPaint) {
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_selected)
            )
            mImageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )
            mImageButtonCurrentPaint = view
        }
    }
}