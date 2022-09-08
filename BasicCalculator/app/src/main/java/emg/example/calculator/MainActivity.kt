package emg.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    private var textViewInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewInput = findViewById(R.id.textViewInput)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener {
            textViewInput?.text = ""
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun onDigit(view: View) {
        textViewInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClr(view: View) {
        textViewInput?.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            textViewInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        textViewInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                textViewInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = textViewInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1);
                }
                when {
                    tvValue.contains("/") -> {
                        val splitedValue = tvValue.split("/")
                        var one = splitedValue[0]
                        val two = splitedValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        textViewInput?.text =
                            removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                    tvValue.contains("*") -> {
                        val splitedValue = tvValue.split("*")

                        var one = splitedValue[0]
                        val two = splitedValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        textViewInput?.text =
                            removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }
                    tvValue.contains("-") -> {

                        val splitedValue = tvValue.split("-")

                        var one = splitedValue[0]
                        val two = splitedValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        textViewInput?.text =
                            removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                    tvValue.contains("+") -> {
                        val splitedValue = tvValue.split("+")

                        var one = splitedValue[0]
                        val two = splitedValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        textViewInput?.text =
                            removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {

        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") ||
                    value.contains("+") || value.contains("-")
        }
    }
}