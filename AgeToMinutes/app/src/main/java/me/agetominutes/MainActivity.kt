package me.agetominutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateSelectorButton.setOnClickListener { view ->
            onDateSelectorButtonClick(view)
        }
    }

    private fun onDateSelectorButtonClick(view: View) {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, month, day ->

            updateSelectedDateTextView(year, month, day)

            val selectedDate = buildDate(year, month, day)

            val ageInMinutes = computeAgeInMinutes(selectedDate)
            updateAgeInMinutesTextView(ageInMinutes)

            val ageInDays = computeAgeInDays(selectedDate)
            updateAgeInDaysTextView(ageInDays)

        }, year, month, day)
        dpd.datePicker.maxDate = Date().time - (24 * 60 * 60 * 1000)
        dpd.show()
    }

    private fun updateSelectedDateTextView(year: Int, month: Int, day: Int) {

        selectedDateTextView.text = formatDate(year, month, day)
    }

    private fun buildDate(year: Int, month: Int, day: Int) = SimpleDateFormat("dd/MM/yyyy").parse(formatDate(year, month, day))

    private fun computeAgeInMinutes(date: Date): Long {

        // we know 'date' cannot be null; we use '!!' to force the retrieval of the value in any case
        return ( (Date().time - date!!.time) / 1000) / 60
    }

    private fun computeAgeInDays(date: Date): Long {

         // we know 'date' cannot be null; we use '!!' to force the retrieval of the value in any case
        return ((Date().time - date!!.time) / 1000)  / (60 * 60 * 24)
    }

    private fun updateAgeInMinutesTextView(ageInMinutes: Long) {

        ageInMinutesTextView.text = "$ageInMinutes"
    }

    private fun updateAgeInDaysTextView(ageInDays: Long) {

        ageInDaysTextView.text = "$ageInDays"
    }

    private fun formatDate(year: Int, month: Int, day: Int) = String.format("%02d/%02d/%d", day, month + 1, year)
}