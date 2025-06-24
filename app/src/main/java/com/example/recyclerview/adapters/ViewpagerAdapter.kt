package com.example.recyclerview.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerAdapter(fb: FragmentActivity, private val lists: List<Fragment>): FragmentStateAdapter(fb) {
    override fun createFragment(position: Int): Fragment {
        return lists[position]
    }

    override fun getItemCount(): Int {
        return lists.size
    }
}