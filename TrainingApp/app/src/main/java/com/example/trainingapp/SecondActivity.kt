package com.example.trainingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.trainingapp.fragments.FirstFragment
import com.example.trainingapp.fragments.SecondFragment

class SecondActivity : AppCompatActivity(), ConfirmDialogFragment.DialogListener {

    companion object {
        val TAG = SecondActivity::class.simpleName
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // ダイアログ表示ボタンを取得する
        val buttonShowDialog: Button = findViewById(R.id.button_show_dialog)
        buttonShowDialog.setOnClickListener {
            Log.i(TAG, "ダイアログ表示ボタンが押されました")
            val dialog = ConfirmDialogFragment()
            dialog.show(supportFragmentManager, "ConfirmDialogFragment")
        }

        // フラグメント1へ切り替えボタンを取得する
        val buttonFragment1: Button = findViewById(R.id.button_fragment1)
        buttonFragment1.setOnClickListener {
            val fragment1 = FirstFragment.createInstance("Fragment 1")
            replaceFragment(fragment1)
            findViewById<TextView>(R.id.textview_second).visibility = View.GONE // 編集画面を非表示
        }

        // フラグメント2へ切り替えボタンを取得する
        val buttonFragment2: Button = findViewById(R.id.button_fragment2)
        buttonFragment2.setOnClickListener {
            replaceFragment(SecondFragment())
            findViewById<TextView>(R.id.textview_second).visibility = View.GONE // 編集画面を非表示
        }

        // 編集画面へボタンを取得する
        val buttonEdit: Button = findViewById(R.id.button_change_edit)
        buttonEdit.setOnClickListener {
            // 編集画面へのアクティビティを開始する
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDialogPositiveClick() {
        Log.i(TAG, "OKボタンが押されました")
        // OKボタンが押されたときの処理
        val intent = Intent(this, OkActivity::class.java)
        startActivity(intent)
    }

    override fun onDialogNegativeClick() {
        Log.i(TAG, "CANCELボタンが押されました")
        // CANCELボタンが押されたときの処理
        val intent = Intent(this, CancelActivity::class.java)
        startActivity(intent)
    }

    override fun onDialogNeutralClick() {
        Log.i(TAG, "後で通知ボタンが押されました")
        // 後で通知ボタンが押されたときの処理
        val intent = Intent(this, LaterNotificationActivity::class.java)
        startActivity(intent)
    }
}
