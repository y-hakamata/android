package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;  // Rクラスを正しくインポートする

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButton(R.id.button_key_clear, "Clear");
        setupButton(R.id.button_key_division, "Divide");
        setupButton(R.id.button_key_equal, "Equal");
        setupButton(R.id.button_key_multiplication, "Multiply");
        setupButton(R.id.button_key_subtraction, "Subtract");
        setupButton(R.id.button_key_addition, "Add");
    }

    private fun setupButton(buttonId: Int, operation: String) {
        val button = findViewById<Button>(buttonId);
        button.setOnClickListener {
            Toast.makeText(
                    this@MainActivity,
            "$operation ボタンがクリックされました",
                    Toast.LENGTH_SHORT
            ).show();
        };
    }
}
