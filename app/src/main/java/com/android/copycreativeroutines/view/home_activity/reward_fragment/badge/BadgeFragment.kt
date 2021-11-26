package com.android.copycreativeroutines.view.home_activity.reward_fragment.badge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.copycreativeroutines.databinding.FragmentBadgeBinding

class BadgeFragment : Fragment() {

    private lateinit var binding : FragmentBadgeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBadgeBinding.inflate(inflater, container, false)
        return binding.root
    }
}