package com.tecra.cloverly.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.tecra.cloverly.R
import com.tecra.cloverly.fragments.*

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var notes: Notes
    lateinit var micFragment: MicFragment
    lateinit var about: ProfileFragment

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayout,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
            .commit()

        val bnve = findViewById<View>(R.id.bnve) as BottomNavigationViewEx
        bnve.enableItemShiftingMode(false)
        bnve.enableShiftingMode(false)
        bnve.enableAnimation(false)
        bnve.setTextVisibility(false)

        bnve.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when(menuItem.itemId){
                R.id.home ->{
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.framelayout,homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                    true
                }
                R.id.notes ->{
                    notes = Notes()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.framelayout,notes)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                    true
                }
                R.id.records ->{
                    micFragment = MicFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.framelayout,micFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                    true
                }
                R.id.leaf ->{
                    about= ProfileFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.framelayout,about)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
