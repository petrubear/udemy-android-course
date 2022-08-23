package emg.example.dobcalc

import android.content.Context

interface MainInterfaces {
    interface View {
        fun context(): Context
        fun updateSelectedDate(text: String)
        fun updateAgeInMinutes(text: String)
    }

    interface Presenter {
        fun showDatePicker()
    }
}