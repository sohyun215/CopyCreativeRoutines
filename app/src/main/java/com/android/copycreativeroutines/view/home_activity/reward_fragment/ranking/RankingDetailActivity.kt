package com.android.copycreativeroutines.view.home_activity.reward_fragment.ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.ActivityRankingDetailBinding

class RankingDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRankingDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ranking_detail)
        init()
    }

    private fun init() {
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")

        // 추가
    }
}