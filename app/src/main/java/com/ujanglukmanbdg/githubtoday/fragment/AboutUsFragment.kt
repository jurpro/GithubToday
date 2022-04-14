package com.ujanglukmanbdg.githubtoday.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ujanglukmanbdg.githubtoday.R
import com.ujanglukmanbdg.githubtoday.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackToMain.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_aboutUsFragment_to_nav_home)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}