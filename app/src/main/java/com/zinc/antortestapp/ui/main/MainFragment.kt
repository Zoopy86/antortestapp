package com.zinc.antortestapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zinc.antortestapp.R
import com.zinc.antortestapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        setupTabLayout()
        setupFab()
    }

    private fun setupTabLayout() {
//        val tabLayout = binding.tabLayout
//        tabLayout.addTab(tabLayout.newTab().setText("Entries"))
//        tabLayout.addTab(tabLayout.newTab().setText("Entries edit"))
//        tabLayout.addTab(tabLayout.newTab().setText("Statistics"))
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                tab?.position?.let { position ->
//                    when (position) {
//                        0 -> findNavController().navigate(R.id.action_mainFragment_to_entriesFragment)
//                        1 -> findNavController().navigate(R.id.action_mainFragment_to_entriesEditFragment)
//                        2 -> findNavController().navigate(R.id.action_mainFragment_to_statisticsFragment)
//                    }
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//        })

        binding.viewPager.adapter = FragmentAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Entries"
                1 -> tab.text = "Entries Edit"
                2 -> tab.text = "Statistics"
            }
        }.attach()
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_newEntryFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    val tabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.position?.let { position ->
                when (position) {
                    0 -> findNavController().navigate(R.id.action_mainFragment_to_entriesFragment)
                    1 -> findNavController().navigate(R.id.action_mainFragment_to_entriesEditFragment)
                    2 -> findNavController().navigate(R.id.action_mainFragment_to_statisticsFragment)
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }

}