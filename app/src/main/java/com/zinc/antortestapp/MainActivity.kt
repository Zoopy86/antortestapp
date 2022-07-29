package com.zinc.antortestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.tabs.TabLayout
import com.zinc.antortestapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController)
        setupTabLayout()
        setupFab()
    }

    override fun onSupportNavigateUp(): Boolean {
        binding.tabLayout.getTabAt(0)?.select()
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        binding.tabLayout.getTabAt(0)?.select()
        super.onBackPressed()
    }

    private fun setupTabLayout() {
        val navOptions: NavOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(navController.graph.startDestinationId, false)
            .build()

        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Entries"))
        tabLayout.addTab(tabLayout.newTab().setText("Entries edit"))
        tabLayout.addTab(tabLayout.newTab().setText("Statistics"))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { position ->
                    when (position) {
                        0 -> navController.navigate(R.id.entriesFragment, null, navOptions)
                        1 -> navController.navigate(R.id.entriesEditFragment, null, navOptions)
                        2 -> navController.navigate(R.id.statisticsFragment, null, navOptions)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            navController.navigate(R.id.newEntryFragment)
        }
    }

}