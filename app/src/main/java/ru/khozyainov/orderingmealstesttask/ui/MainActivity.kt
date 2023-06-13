package ru.khozyainov.orderingmealstesttask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.utils.setDefaultState

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDateInSubtitle()
        setUpBottomNavigation()
    }

    private fun setDateInSubtitle() {
        val toolbar: Toolbar = findViewById(R.id.appToolBar)
        setSupportActionBar(toolbar)
        toolbar.setDefaultState(this)
    }

    private fun setUpBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.viewFragmentContainer
        ) as NavHostFragment

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.viwBottomNavigation)
        bottomNavigation.apply {
            setupWithNavController(navHostFragment.navController)
        }
    }
}