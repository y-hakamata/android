package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        val navController = findNavController(this, R.id.nav_host_fragment_activity_main)

        // AppBarConfiguration を適切に構築する例
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_calculator
        ).build()

        // setupActionBarWithNavController を適切に呼び出す
        setupActionBarWithNavController(this, navController, appBarConfiguration)

        // BottomNavigationView と NavController を連携する
        navView.setupWithNavController(navController)
    }

    private fun setupActionBarWithNavController(
        mainActivity: MainActivity,
        navController: NavController,
        appBarConfiguration: AppBarConfiguration
    ) {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
