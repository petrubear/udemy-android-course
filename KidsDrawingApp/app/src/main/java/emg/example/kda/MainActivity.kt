package emg.example.kda

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import emg.example.kda.views.DrawingView

class MainActivity : AppCompatActivity() {
    private var drawingView: DrawingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.0f)

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
}