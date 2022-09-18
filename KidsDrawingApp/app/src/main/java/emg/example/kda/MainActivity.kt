package emg.example.kda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import emg.example.kda.views.DrawingView

class MainActivity : AppCompatActivity() {
    private var drawingView: DrawingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.0f)
    }
}