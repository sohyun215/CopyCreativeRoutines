package com.android.copycreativeroutines.view.home_activity.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.FragmentHomeBinding
import com.android.copycreativeroutines.view.home_activity.Schedule
import com.prolificinteractive.materialcalendarview.CalendarDay

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    fun init(){
        val calendarView=binding.calendarView
        val today=CalendarDay.today()
        calendarView.setSelectedDate(today)

        if(calendarView.currentDate.equals(today))
            calendarView.setDateTextAppearance(R.style.calender_todayTextStyle)


        val test= ArrayList<Schedule>()
        test.add(Schedule("찰스다윈","기상","AM 9:30"))
        test.add(Schedule("찰스다윈","가벼운 아침 산책","AM 10:00 - AM 11:30"))
        test.add(Schedule("찰스다윈","점심 식사","PM 12:00"))

        scheduleAdapter=ScheduleAdapter(test)
        binding.rvSchedule.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        binding.rvSchedule.adapter=scheduleAdapter

    }


}