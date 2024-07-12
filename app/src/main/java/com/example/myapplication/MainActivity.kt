package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.myapplication.ui.HomeFragment
import com.example.myapplication.ui.DetailFragment


class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.textView_result)

        // Set click listeners for number buttons
        val numberButtonIds = arrayOf(
            R.id.button_key_00, R.id.button_key_01, R.id.button_key_02, R.id.button_key_03,
            R.id.button_key_04, R.id.button_key_05, R.id.button_key_06, R.id.button_key_07,
            R.id.button_key_08, R.id.button_key_09
        )

        numberButtonIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                appendTextToResult(findViewById<Button>(buttonId).text.toString())
            }
        }

        // Set click listeners for operation buttons
        val operationButtonIds = arrayOf(
            R.id.button_key_addition, R.id.button_key_subtraction,
            R.id.button_key_multiplication, R.id.button_key_divide
        )

        operationButtonIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                performOperation(findViewById<Button>(buttonId).text.toString())
            }
        }

        // Clear button click listener
        findViewById<Button>(R.id.button_key_clear).setOnClickListener {
            clearResult()
        }

        // Equal button click listener
        findViewById<Button>(R.id.button_key_equal).setOnClickListener {
            calculateResult()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun appendTextToResult(text: String) {
        val currentText = textViewResult.text.toString()
        textViewResult.text = "$currentText$text"
    }

    @SuppressLint("SetTextI18n")
    private fun clearResult() {
        textViewResult.text = ""
    }

    @SuppressLint("SetTextI18n")
    private fun performOperation(operation: String) {
        val currentText = textViewResult.text.toString()
        textViewResult.text = "$currentText $operation "
    }

    private fun calculateResult() {
        val expression = textViewResult.text.toString()
        try {
            val result = evaluateExpression(expression)
            textViewResult.text = result.toString()
        } catch (e: ArithmeticException) {
            Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show()
        }
    }

    private fun evaluateExpression(expression: String): Double {
        // Implement your own logic to evaluate the expression
        val parts = expression.split(" ")
        val num1 = parts[0].toDouble()
        val operation = parts[1]
        val num2 = parts[2].toDouble()

        return when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> throw ArithmeticException("Invalid operation")
        }
    }
}
