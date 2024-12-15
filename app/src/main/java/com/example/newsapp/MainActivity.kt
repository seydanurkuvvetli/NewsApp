package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.annotation.meta.When

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)

        binding.apply {
            navigationDrawer.setNavigationItemSelectedListener(this@MainActivity)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            buttonDrawerMenu.setOnClickListener { drawerLayout.open() }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemLocation -> {
                Toast.makeText(this, "coming soon..", Toast.LENGTH_SHORT).show()

            }

            R.id.itemShare -> {
                Toast.makeText(this, "coming soon..", Toast.LENGTH_SHORT).show()
            }

            R.id.itemReview -> {
                Toast.makeText(this, "coming soon..", Toast.LENGTH_SHORT).show()
            }
        }
        binding.drawerLayout.close()
        return true
    }
    companion object {
        private val TAG = MainActivity::class.simpleName.toString()
    }
}