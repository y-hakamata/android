package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var num1 = 0.0
    private var num2 = 0.0
    private var operator: Char = ' '
    private var operatorPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.textView_result)
        setupButtons()
    }

    private fun setupButtons() {
        // ボタンのIDリストを準備する
        val buttonIds = listOf(
            R.id.button_key_clear, R.id.button_key_00, R.id.button_key_01, R.id.button_key_02,
            R.id.button_key_03, R.id.button_key_04, R.id.button_key_05, R.id.button_key_06,
            R.id.button_key_07, R.id.button_key_08, R.id.button_key_09, R.id.button_key_plus,
            R.id.button_key_minus, R.id.button_key_x, R.id.button_key_equal, R.id.button_key_divide
        )

        // 各ボタンにクリックリスナーを設定する
        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        val buttonText = (view as Button).text.toString()

        when (buttonText) {
            getString(R.string.button_clear) -> {
                resultTextView.text = ""
                num1 = 0.0
                num2 = 0.0
                operatorPressed = false
            }
            "+", "-", "*", "/" -> {
                operator = buttonText[0]
                num1 = resultTextView.text.toString().toDouble()
                resultTextView.text = ""
                operatorPressed = true
            }
            getString(R.string.button_equal) -> {
                if (operatorPressed) {
                    num2 = resultTextView.text.toString().toDouble()
                    val result = calculateResult()
                    resultTextView.text = result.toString()
                    operatorPressed = false
                }
            }
            else -> { // 数字ボタンの場合
                resultTextView.append(buttonText)
            }
        }
    }

    private fun calculateResult(): Double {
        return when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> num1 / num2
            else -> 0.0
        }
    }
}
