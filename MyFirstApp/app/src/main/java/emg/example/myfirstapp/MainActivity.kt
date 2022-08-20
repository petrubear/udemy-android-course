package emg.example.myfirstapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {
    private var counter = AtomicInteger(0)
    private lateinit var textViewCounter: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCounter = findViewById(R.id.textViewCounter)
        val buttonClickMe = findViewById<Button>(R.id.buttonClickMe)

        buttonClickMe.setOnClickListener {
            updateText {
                countUp()
            }
        }
        val buttonReset = findViewById<Button>(R.id.buttonReset)
        buttonReset.setOnClickListener {
            updateText {
                reset()
            }
        }
    }

    private fun countUp(): Int {
        return counter.addAndGet(1)
    }

    private fun reset(): Int {
        val initialCount = 0
        counter.set(initialCount)
        Toast.makeText(
            this, "Count was reset to $initialCount!",
            Toast.LENGTH_SHORT
        ).show()
        return initialCount
    }

    private fun updateText(function: () -> Int) {
        textViewCounter.text = function.invoke().toString()
    }
}