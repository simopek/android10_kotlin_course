package me.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var digitButtonPressed = false
    var decimalPointButtonPressed = false
    var operatorButtonPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCLRButtonClick(view: View) {
        resultTextView.text = ""
        digitButtonPressed = false
        decimalPointButtonPressed = false
        operatorButtonPressed = false
    }

    fun onDigitButtonClick(view: View) {
        resultTextView.append((view as Button).text)
        digitButtonPressed = true
    }

    fun onDecimalPointButtonClick(view: View) {

        if (!digitButtonPressed || decimalPointButtonPressed) {
            return
        }

        resultTextView.append(".")
        decimalPointButtonPressed = true
    }

    fun onOperatorButtonClick(view: View) {

        val currResultText = resultTextView.text

        if (currResultText.isBlank() && ("-" == (view as Button).text)) {
            // we are adding the "minus" for a likely negative number
            // in the text view still blank

            resultTextView.append("-")
            return
        }

        if (operatorButtonPressed) {
            // an operator button has already been pressed; in this case
            // we are adding the "-" to a number after the operator

            if ( ( (currResultText.endsWith("*") ||
                        currResultText.endsWith("+") || currResultText.endsWith("/")) ) && ("-" == (view as Button).text)) {
                resultTextView.append("-")
            }

            return
        }

        if (!digitButtonPressed) {
            return
        }

        operatorButtonPressed = true
        // we give the opportunity to enter the second number
        digitButtonPressed = false
        decimalPointButtonPressed = false

        resultTextView.append((view as Button).text)
    }
}