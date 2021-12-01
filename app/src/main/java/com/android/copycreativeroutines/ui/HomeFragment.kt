package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.HomeScheduleAdapter
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.FragmentHomeBinding
import com.prolificinteractive.materialcalendarview.CalendarDay

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeScheduleAdapter: HomeScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    fun init() {
        val calendarView = binding.calendarView
        val today = CalendarDay.today()
        calendarView.setSelectedDate(today)

        if (calendarView.currentDate.equals(today))
            calendarView.setDateTextAppearance(R.style.calender_todayTextStyle)


        val test = mutableListOf<Great.Schedule>()

        test.addAll(
            listOf(
                Great.Schedule("기상", "AM 9:30", ""),
                Great.Schedule("가벼운 아침 산책", "AM 10:00", "AM 11:30"),
                Great.Schedule("점심 식사", "PM 12:00", "")
            )
        )

        homeScheduleAdapter = HomeScheduleAdapter(test)
        binding.rvSchedule.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rvSchedule.adapter = homeScheduleAdapter

    }


}