package com.ujanglukmanbdg.githubtoday.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ujanglukmanbdg.githubtoday.R
import com.ujanglukmanbdg.githubtoday.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView2.setOnClickListener { viewListUserActivity ->
            viewListUserActivity.findNavController().navigate(R.id.action_nav_home_to_listUserActivity)
        }
        binding.githublogoNew.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_aboutUsFragment)
        )
        binding.imageView3.setOnClickListener { viewListSearchUserActivity ->
            viewListSearchUserActivity.findNavController().navigate(R.id.action_nav_home_to_searchUserActivity)
        }
        binding.imageView5.setOnClickListener { viewFavoriteActivity ->
            viewFavoriteActivity.findNavController().navigate(R.id.action_nav_home_to_myFavoriteActivity)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}