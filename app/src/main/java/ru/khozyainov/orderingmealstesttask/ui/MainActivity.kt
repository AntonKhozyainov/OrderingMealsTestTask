package ru.khozyainov.orderingmealstesttask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import ru.khozyainov.orderingmealstesttask.R

class MainActivity : AppCompatActivity(){

    private val formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy")
        .withZone(ZoneId.systemDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDateInSubtitle()
        setUpBottomNavigation()
    }

    private fun setDateInSubtitle(){
        val toolbar: Toolbar = findViewById(R.id.appToolBar)
        setSupportActionBar(toolbar)
        //supportActionBar?.title = "sdf"
        supportActionBar?.subtitle = formatter.format(Instant.now())
    }

    private fun setUpBottomNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.viewFragmentContainer
        ) as NavHostFragment

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.viwBottomNavigation)
        bottomNavigation.apply {
            setupWithNavController(navHostFragment.navController)
        }
    }
}