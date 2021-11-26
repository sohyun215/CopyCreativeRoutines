package com.android.copycreativeroutines.view.home_activity.reward_fragment.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.copycreativeroutines.databinding.FragmentRankingBinding
import com.android.copycreativeroutines.view.home_activity.User

class RankingFragment : Fragment() {

    private lateinit var binding : FragmentRankingBinding
    private lateinit var rankingRVAdapter: RankingRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        initAdapter()

    }

    private fun initAdapter() {
        rankingRVAdapter = RankingRVAdapter()
        binding.rvRanking.adapter = rankingRVAdapter

        rankingRVAdapter.userList.addAll( // 유저 추가
            listOf(
                User("","User1",101),
                User("","User2",102),
                User("","User3",103),
                User("","User4",104),
                User("","User5",105),
                User("","User6",106),
                User("","User7",107),
                User("","User8",108)
            )
        )
    }
}