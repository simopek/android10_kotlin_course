package me.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var digitPressed = false
    var decimalPointPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCLRButton(view: View) {
        resultTextView.text = ""
        digitPressed = false
        decimalPointPressed = false
    }

    fun onDigitButton(view: View) {
        resultTextView.append((view as Button).text)
        digitPressed = true
    }

    fun onDecimalPointButton(view: View) {

        if (!digitPressed || decimalPointPressed) {
            return
        }

        resultTextView.append(".")
        decimalPointPressed = true
    }
}