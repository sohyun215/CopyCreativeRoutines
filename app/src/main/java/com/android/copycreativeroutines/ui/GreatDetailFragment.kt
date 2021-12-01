package com.android.copycreativeroutines.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.ScheduleSelectAdapter
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.FragmentGreatDetailBinding

class GreatDetailFragment : Fragment() {
    private lateinit var binding : FragmentGreatDetailBinding
    private lateinit var scheduleSelectAdapter : ScheduleSelectAdapter
    private var listener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGreatDetailBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        initView()
        initBtn()
        initAdapter()
    }

    private fun initView() {
//        GreatsFragment에서 클릭한 인물의 정보를 받아서 띄워주기

//        val image = intent.getStringExtra("image")
//        val name = intent.getStringExtra("name")

//        Glide.with(this)
//            .load(image)
//            .circleCrop()
//            .into(binding.ivGreatImage)
//        binding.tvGreatName.text = name
//        binding.tvGreatInfo.text = "찰스 로버트 다윈은 영국의 생물학자이자 지질학자로서 진화론에 기여가 가장 크다고 알려져 있다."
    }

    private fun initBtn() {
        binding.ivBackBtn.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fc_home, GreatsFragment())
                .commit()
        }

        binding.ivAddBtn.setOnClickListener {
            addSchedule()
            listener?.onFragmentInteraction()
        }
    }

    private fun addSchedule() {
        // 일정 추가 기능 구현

    }

    private fun initAdapter() {
        scheduleSelectAdapter = ScheduleSelectAdapter()
        binding.rvScheduleList.adapter = scheduleSelectAdapter

        scheduleSelectAdapter.sheduleList.addAll( // schedule 서버 api 구현 필요
            listOf(
                Great.Schedule("기상","AM 7:00", ""),
                Great.Schedule("가벼운 산책","AM 7:00","AM 7:30"),
                Great.Schedule("아침 식사","AM 7:30","AM 8:00"),
                Great.Schedule("일&편지 읽기","AM 8:00","AM 12:00")
            )
        )
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }
}