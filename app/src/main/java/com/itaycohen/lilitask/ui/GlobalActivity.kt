package com.itaycohen.lilitask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.itaycohen.lilitask.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * The Main Single-Activity for this app.
 */
@AndroidEntryPoint
class GlobalActivity : AppCompatActivity() {

    /**
     * attempting to retrieve the NavController in onCreate() of an Activity
     * via Navigation.findNavController(Activity, @IdRes int) will fail.
     * Use this instead.
     */
    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}