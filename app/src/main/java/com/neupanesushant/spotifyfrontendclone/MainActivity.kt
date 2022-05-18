package com.neupanesushant.spotifyfrontendclone

import android.os.Build
import android.os.Build.VERSION_CODES.KITKAT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import com.neupanesushant.spotifyfrontendclone.databinding.LibraryBottomDialogSheetBinding
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
    private lateinit var bottomSheetBinding : LibraryBottomDialogSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bottomSheetBinding = LibraryBottomDialogSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDefaultFragment()
        setCurrentSortingLayout()

        val navigationBarView: NavigationBarView = findViewById(R.id.bottom_navigation)
        currentFragmentListener(navigationBarView)


    }

    fun setCurrentSortingLayout(){
        libraryFragment.currentSortingLayout = LibrarySortSetting(bottomSheetBinding.rlMostRecent, bottomSheetBinding.tvMostRecent, bottomSheetBinding.ivMostRecent, "Most Recent", dataLibraryContentList)
        libraryFragment.previousSortingLayout = libraryFragment.currentSortingLayout
    }


    fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.isAddToBackStackAllowed
//            fragmentTransaction.setReorderingAllowed(true)
            fragmentTransaction.addToBackStack(null)
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