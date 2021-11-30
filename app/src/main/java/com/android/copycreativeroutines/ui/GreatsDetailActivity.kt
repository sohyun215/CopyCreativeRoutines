package com.android.copycreativeroutines.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.ScheduleSelectAdapter
import com.android.copycreativeroutines.data.Schedule
import com.android.copycreativeroutines.databinding.ActivityGreatsDetailBinding
import com.bumptech.glide.Glide

class GreatsDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGreatsDetailBinding
    private lateinit var scheduleSelectAdapter : ScheduleSelectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_greats_detail)
        init()
    }

    private fun init() {
        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")

        Glide.with(this)
            .load(image)
            .circleCrop()
            .into(binding.ivGreatImage)
        binding.tvGreatName.text = name
        binding.tvGreatInfo.text = "찰스 로버트 다윈은 영국의 생물학자이자 지질학자로서 진화론에 기여가 가장 크다고 알려져 있다."

        initBtn()
        initAdapter()
    }

    private fun initBtn() {
        binding.ivBackBtn.setOnClickListener {
            finish()
        }

        binding.ivAddBtn.setOnClickListener {
            // 일정 추가 기능 구현
            finish()
        }
    }

    private fun initAdapter() {
        scheduleSelectAdapter = ScheduleSelectAdapter()
        binding.rvScheduleList.adapter = scheduleSelectAdapter

        scheduleSelectAdapter.sheduleList.addAll( // schedule 서버 api 구현 필요
            listOf(
                Schedule("찰스다윈","기상","AM 7:00", ""),
                Schedule("찰스다윈","가벼운 산책","AM 7:00","AM 7:30"),
                Schedule("찰스다윈","아침 식사","AM 7:30","AM 8:00"),
                Schedule("찰스다윈","일&편지 읽기","AM 8:00","AM 12:00")
            )
        )
    }
}