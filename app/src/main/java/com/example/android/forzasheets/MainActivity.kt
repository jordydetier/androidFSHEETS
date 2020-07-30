package com.example.android.forzasheets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.forzasheets.database.ForzaSheetsDatabase
import com.example.android.forzasheets.databinding.ActivityMainBinding
import com.example.android.forzasheets.helper.InflateDatabase


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


       val dataSource = ForzaSheetsDatabase.getInstance(application).forzaSheetsDatabaseDao
       InflateDatabase(dataSource).inflateDb()

        drawerLayout = binding.drawerLayout
        navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    navController.popBackStack(R.id.homeFragment, false)
                    drawerLayout.closeDrawers()
                    true

                }
                R.id.standingsFragmentPremierLeague -> {
                    navController.popBackStack(R.id.homeFragment, false)
                    navController.navigate(R.id.standingsFragmentPremierLeague)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.standingsFragmentBundesliga -> {
                    navController.popBackStack(R.id.homeFragment, false)
                    navController.navigate(R.id.standingsFragmentBundesliga)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.standingsFragmentLaLiga -> {
                    navController.popBackStack(R.id.homeFragment, false)
                    navController.navigate(R.id.standingsFragmentLaLiga)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.standingsFragmentSerieA -> {
                    navController.popBackStack(R.id.homeFragment, false)
                    navController.navigate(R.id.standingsFragmentSerieA)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.standingsFragmentLigue1 -> {
                    navController.popBackStack(R.id.homeFragment, false)
                    navController.navigate(R.id.standingsFragmentLigue1)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.standingsFragmentEredivisie -> {
                    navController.popBackStack(R.id.homeFragment, false)
                    navController.navigate(R.id.standingsFragmentEredivisie)
                    drawerLayout.closeDrawers()
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
