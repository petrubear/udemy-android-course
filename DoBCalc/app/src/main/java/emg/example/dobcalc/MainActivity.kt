package emg.example.dobcalc

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainInterfaces.View {
    private lateinit var presenter: MainInterfaces.Presenter
    private lateinit var textSelectedDate: TextView
    private lateinit var textAgeInMinutes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)
        setContentView(R.layout.activity_main)

        textSelectedDate = findViewById(R.id.selectedDate)
        textAgeInMinutes = findViewById(R.id.ageInMinutes)
        val buttonDatePicker = findViewById<Button>(R.id.buttonDatePicker)
        buttonDatePicker.setOnClickListener {
            presenter.showDatePicker()
        }
    }

    override fun context(): Context {
        return this
    }

    override fun updateSelectedDate(text: String) {
        textSelectedDate.text = text
    }

    override fun updateAgeInMinutes(text: String) {
        textAgeInMinutes.text = text
    }

}