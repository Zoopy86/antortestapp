package com.zinc.antortestapp.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zinc.antortestapp.ui.entries.EntryListFragment
import com.zinc.antortestapp.ui.entries_edit.EntriesEditFragment
import com.zinc.antortestapp.ui.statistics.StatisticsFragment

class FragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> EntryListFragment.newInstance()
        1 -> EntriesEditFragment.newInstance()
        2 -> StatisticsFragment.newInstance()
        else -> EntryListFragment.newInstance()
    }
}