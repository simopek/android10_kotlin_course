package me.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var num1: String = ""
    private var num2: String = ""
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCLRButtonClick(view: View) {
        reset();
    }

    private fun reset() {
        resultTextView.text = ""
        num1 = ""
        num2 = ""
        operator = null
    }

    fun onDigitButtonClick(view: View) {

        val digit = (view as Button).text

        if (operator == null) {
            // we are defining num1
            num1 += digit
        } else {
            // we are defining num2
            num2 += digit
        }
        resultTextView.append(digit)
    }

    fun onDecimalPointButtonClick(view: View) {

        var addDecimalPoint = false

       if (operator == null) {
           // we are defining num1
           if (!num1.isBlank() && !num1.contains(".")) {
               num1 += "."
               addDecimalPoint = true
           }

       } else {
           // we are defining num2
           if (!num2.isBlank() && !num2.contains(".")) {
               num2 += "."
               addDecimalPoint = true
           }
       }

        if (addDecimalPoint) {
            resultTextView.append(".")
        }
    }

    fun onMinusButtonClick(view: View) {

        if (operator == null) {
            // we are defining num1 as a negative number
            if (num1.isBlank()) {

                num1 = "-"
                resultTextView.append("-")

            } else {
                // we handle the "-" as the operator
                __onOperatorButtonClick("-")
            }

            return

        }

        // operator is already defined

        if (num2.isBlank()) {
            num2 = "-"
            resultTextView.append("-")
        }
    }

    fun onOperatorButtonClick(view: View) {

        __onOperatorButtonClick((view as Button).text.toString())
    }

    private fun __onOperatorButtonClick(chosenOperator: String) {

        // all the operators except for "-"
        if (operator != null) {
            return
        }

        operator = chosenOperator
        resultTextView.append(operator)
    }

    fun onEqualButtonClick(view: View) {

        if (operator == null) {
            return
        }

        if (num1.isBlank() || num1 == "-" || num1.endsWith(".")) {
            // num1 is invalid
            return
        }

        if (num2.isBlank() || num2 == "-" || num2.endsWith(".")) {
            // num2 is invalid
            return
        }

        try {

            val num1D = num1.toDouble()
            val num2D = num2.toDouble()

            var result = when(operator) {
                "+" -> "${num1D + num2D}"
                "-" -> "${num1D - num2D}"
                "*" -> "${num1D * num2D}"
                "/" -> "${num1D / num2D}"
                else -> ""
            }

            reset()
            num1 = result
            resultTextView.text = num1

        } catch (e: Exception) {
            reset()
        }

    }

}