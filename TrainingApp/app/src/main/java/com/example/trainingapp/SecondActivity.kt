package com.example.trainingapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.trainingapp.fragments.FirstFragment
import com.example.trainingapp.fragments.SecondFragment

class SecondActivity : AppCompatActivity(), FirstFragment.OnNotifyFragmentListener, SecondFragment.OnNotifyFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Viewの初期設定を行なう
        initView()
    }

    /**
     * Viewの初期設定を行なう.
     */
    private fun initView() {
        // フラグメント１画面へボタンを取得する
        val buttonChangeFragment1: Button = findViewById(R.id.button_change_fragment1)
        // フラグメント２画面へボタンを取得する
        val buttonChangeFragment2: Button = findViewById(R.id.button_change_fragment2)

        // フラグメント１画面へボタンを押下時の処理
        buttonChangeFragment1.setOnClickListener {
            // フラグメントを管理するトランザクション機能を利用する
            val transaction = supportFragmentManager.beginTransaction()
            // レイアウトにfragmentの置換
            transaction.replace(R.id.layout_fragment, FirstFragment.createInstance("fragment1"))
            // バックスタックに追加
            transaction.addToBackStack(null)
            // フラグメントを画面に反映する
            transaction.commit()
        }

        // フラグメント２画面へボタンを押下時の処理
        buttonChangeFragment2.setOnClickListener {
            // フラグメントを管理するトランザクション機能を利用する
            val transaction = supportFragmentManager.beginTransaction()
            // レイアウトにfragmentの置換
            transaction.replace(R.id.layout_fragment, SecondFragment.createInstance("fragment2"))
            // バックスタックに追加
            transaction.addToBackStack(null)
            // フラグメントを画面に反映する
            transaction.commit()
        }
    }

    /**
     * イベント通知リスナーインターフェースのメソッドを実装.
     */
    override fun onClickChangeMain() {
        finish()
    }
}
