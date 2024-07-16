package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput: String = ""
    private var operator: String = ""
    private var firstOperand: Double = 0.0
    private var secondOperand: Double = 0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.textView_result)

        val buttons = listOf(
            R.id.button_key_clear, R.id.button_key_00, R.id.button_key_01,
            R.id.button_key_02, R.id.button_key_03, R.id.button_key_04,
            R.id.button_key_05, R.id.button_key_06, R.id.button_key_07,
            R.id.button_key_08, R.id.button_key_09, R.id.button_key_divide,
            R.id.button_key_multiplication, R.id.button_key_subtraction,
            R.id.button_key_addition, R.id.button_key_equal
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener(this::onButtonClick)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // ホームタブが選択された時の処理
                    true
                }
                R.id.navigation_dashboard -> {
                    // ダッシュボードタブが選択された時の処理
                    true
                }
                R.id.navigation_notifications -> {
                    // 通知タブが選択された時の処理
                    true
                }
                else -> false
            }
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        when (buttonText) {
            "C" -> clear()
            "+", "-", "×", "÷" -> setOperator(buttonText)
            "=" -> calculate()
            else -> appendNumber(buttonText)
        }
    }

    private fun clear() {
        currentInput = ""
        operator = ""
        firstOperand = 0.0
        secondOperand = 0.0
        resultTextView.text = ""
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toDouble()
            operator = op
            currentInput = ""
        }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        resultTextView.text = currentInput
    }

    private fun calculate() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            secondOperand = currentInput.toDouble()
            val result = when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "×" -> firstOperand * secondOperand
                "÷" -> firstOperand / secondOperand
                else -> 0.0
            }
            resultTextView.text = result.toString()
            currentInput = result.toString()
            operator = ""
        }
    }
}
