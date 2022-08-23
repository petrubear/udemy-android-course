package emg.example.dobcalc

import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.*

class MainPresenter(private val view: MainInterfaces.View?) : MainInterfaces.Presenter {
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    override fun showDatePicker() {
        view?.let {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val dialog = DatePickerDialog(
                it.context(),
                { _, selectedYear,
                  selectedMonth,
                  selectedDayOfMonth ->
                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                    it.updateSelectedDate(selectedDate)

                    val date = simpleDateFormat.parse(selectedDate)
                    date?.let { withDate ->
                        val selectedDateInMinutes = withDate.time / 1000 / 60
                        val currentDate =
                            simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                        currentDate?.let { withCurrentDate ->
                            val currentDateInMinutes = withCurrentDate.time / 1000 / 60
                            val diffInMinutes = currentDateInMinutes - selectedDateInMinutes
                            view.updateAgeInMinutes(diffInMinutes.toString())
                        }
                    }
                }, year, month, dayOfMonth
            )
            dialog.datePicker.maxDate = System.currentTimeMillis()
            dialog.show()
        }
    }
}