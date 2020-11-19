package com.example.glatalk_project.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.glatalk_project.view.ErrorFragment
import com.example.glatalk_project.view.HomeFragment
import com.example.glatalk_project.view.MyFragment

class PageAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    val PAGE_MAX_CNT = 2

    override fun getItem(position: Int): Fragment {
        val fragment = when (position){
            0 -> return HomeFragment()
            1 -> return MyFragment()
            else -> ErrorFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }
}