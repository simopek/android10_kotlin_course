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
            val ageInMinutes = computeAgeInMinutes(year, month, day)
            updateAgeInMinutesTextView(ageInMinutes)
        }, year, month, day)
        dpd.show()
    }

    private fun updateSelectedDateTextView(year: Int, month: Int, day: Int) {

        selectedDateTextView.text = String.format("%02d/%02d/%d", day, month + 1, year)
    }

    private fun computeAgeInMinutes(year: Int, month: Int, day: Int): Long {

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.parse(String.format("%02d/%02d/%d", day, month + 1, year))

        return ( (Date().time - date.time) / 1000) / 60
    }

    private fun updateAgeInMinutesTextView(ageInMinutes: Long) {

        ageInMinutesTextView.text = "$ageInMinutes"
    }
}