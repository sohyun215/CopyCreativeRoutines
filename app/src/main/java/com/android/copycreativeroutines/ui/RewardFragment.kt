package com.android.copycreativeroutines.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.FragmentRewardBinding

class RewardFragment : Fragment() {

    private lateinit var binding : FragmentRewardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRewardBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val rankingFragment = RankingFragment()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fcv_reward, rankingFragment)
            .commit()

        initBtn()

    }

    private fun initBtn() {
        binding.btnRanking.setOnClickListener {
            transFragment(FOLLOWER_BTN)
        }

        binding.btnBadge.setOnClickListener {
            transFragment(REPOSITORY_BTN)
        }
    }

    private fun transFragment(btn : Int) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        when(btn) {
            FOLLOWER_BTN -> {
                val rankingFragment = RankingFragment()
                transaction.replace(R.id.fcv_reward, rankingFragment).commit()

            }
            REPOSITORY_BTN -> {
                val badgeFragment = BadgeFragment()
                transaction.replace(R.id.fcv_reward, badgeFragment).commit()
            }
        }
    }

    companion object {
        const val FOLLOWER_BTN = 1
        const val REPOSITORY_BTN = 2
    }
}