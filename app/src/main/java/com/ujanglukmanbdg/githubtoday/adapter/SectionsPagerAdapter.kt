package com.ujanglukmanbdg.githubtoday.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ujanglukmanbdg.githubtoday.fragment.FollowersFragment
import com.ujanglukmanbdg.githubtoday.fragment.FollowersMeFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowersFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowersFragment.ARG_SECTION_NUMBER, position + 1)
        }

        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

}

class SectionsPagerAdapterAboutMe(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowersMeFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowersMeFragment.ARG_SECTION_NUMBER, position + 1)
        }

        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

}