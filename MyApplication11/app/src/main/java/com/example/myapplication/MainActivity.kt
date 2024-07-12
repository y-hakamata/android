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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.textView_result)

        setupNumberButtons()
        setupOperators()
        setupClearButton() // クリアボタンのセットアップを追加
        setupBottomNavigationView()
    }

    private fun setupNumberButtons() {
        val numberButtonIds = listOf(
            R.id.button_key_00, R.id.button_key_01,
            R.id.button_key_02, R.id.button_key_03, R.id.button_key_04,
            R.id.button_key_05, R.id.button_key_06, R.id.button_key_07,
            R.id.button_key_08, R.id.button_key_09
        )

        numberButtonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener(this::onNumberButtonClick)
        }
    }

    private fun setupOperators() {
        val operatorButtonIds = listOf(
            R.id.button_key_division, R.id.button_key_multiplication,
            R.id.button_key_subtraction, R.id.button_key_addition, R.id.button_key_equal
        )

        operatorButtonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener(this::onOperatorButtonClick)
        }
    }

    // クリアボタンのセットアップを追加
    private fun setupClearButton() {
        findViewById<Button>(R.id.button_key_clear).setOnClickListener {
            clear()
        }
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // ホーム画面が選択されたときの処理
                    true
                }
                R.id.navigation_dashboard -> {
                    // ダッシュボードが選択されたときの処理
                    true
                }
                R.id.navigation_notifications -> {
                    // 通知が選択されたときの処理
                    true
                }
                else -> false
            }
        }
    }

    private fun onNumberButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()
        appendNumber(buttonText)
    }

    private fun onOperatorButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()
        setOperator(buttonText)
    }

    @SuppressLint("SetTextI18n")
    private fun clear() {
        currentInput = ""
        operator = ""
        firstOperand = 0.0
        secondOperand = 0.0
        resultTextView.text = ""
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            if (operator.isNotEmpty() && operator != op) {
                calculate()
            }
            firstOperand = currentInput.toDouble()
            operator = op
            currentInput = ""
        } else {
            operator = op
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

            val roundedResult = "%.2f".format(result)
            resultTextView.text = roundedResult

            currentInput = roundedResult
            operator = ""
        }
    }
}
