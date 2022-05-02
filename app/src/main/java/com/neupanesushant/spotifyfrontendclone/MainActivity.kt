package com.neupanesushant.spotifyfrontendclone

import android.os.Build
import android.os.Build.VERSION_CODES.KITKAT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.databinding.ActivityMainBinding
import com.neupanesushant.spotifyfrontendclone.fragments.HomeFragment
import com.neupanesushant.spotifyfrontendclone.fragments.LibraryFragment
import com.neupanesushant.spotifyfrontendclone.fragments.PremiumFragment
import com.neupanesushant.spotifyfrontendclone.fragments.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment();
    private val libraryFragment = LibraryFragment();
    private val premiumFragment = PremiumFragment();
    private val searchFragment = SearchFragment();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDefaultFragment()

        val navigationBarView: NavigationBarView = findViewById(R.id.bottom_navigation)
        currentFragmentListener(navigationBarView)


    }

    fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }

    fun currentFragmentListener(navigationBarView: NavigationBarView) {
        navigationBarView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment)
                    showTransparentStatusBar()
                }
                R.id.search -> {
                    replaceFragment(searchFragment)
                    removeTransparentStatusBar()
                }
                R.id.library -> {
                    replaceFragment(libraryFragment)
                    removeTransparentStatusBar()
                }
                R.id.premium -> {
                    replaceFragment(premiumFragment)
                    showTransparentStatusBar()
                }

            }
            true
        }
    }

    fun loadDefaultFragment() {
        replaceFragment(homeFragment)
        showTransparentStatusBar()
    }

    fun showTransparentStatusBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    fun removeTransparentStatusBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

}