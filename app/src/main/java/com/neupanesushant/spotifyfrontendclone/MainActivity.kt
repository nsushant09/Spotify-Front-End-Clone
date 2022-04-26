package com.neupanesushant.spotifyfrontendclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private lateinit var binding : ActivityMainBinding
    private val homeFragment = HomeFragment();
    private val libraryFragment = LibraryFragment();
    private val premiumFragment = PremiumFragment();
    private val searchFragment = SearchFragment();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(homeFragment)

        val navigationBarView : NavigationBarView = findViewById(R.id.bottom_navigation)
        navigationBarView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.search -> replaceFragment(searchFragment)
                R.id.library -> replaceFragment(libraryFragment)
                R.id.premium -> replaceFragment(premiumFragment)
            }
            true
        }

    }

    fun replaceFragment(fragment : Fragment){
        if(fragment != null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }

}