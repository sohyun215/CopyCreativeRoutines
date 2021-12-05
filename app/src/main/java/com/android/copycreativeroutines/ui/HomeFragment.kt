package com.android.copycreativeroutines.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.HomeScheduleAdapter
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.FragmentHomeBinding
import com.android.copycreativeroutines.util.FBAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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
        val ymText=binding.calendarYm
        val pickerBtn=binding.pickerBtn

        calendarView.topbarVisible=false
        calendarView.isDynamicHeightEnabled=true
        val today = CalendarDay.today() // yyyy-mm-dd
        calendarView.selectedDate = today
        ymText.text=today.year.toString()+"."+today.month.toString()
        if (calendarView.currentDate.equals(today))
            calendarView.setDateTextAppearance(R.style.calender_todayTextStyle)

        val datePickerDialog=DatePickerDialog(requireContext(),R.style.DatePicker,DatePickerDialog.OnDateSetListener {
                view, year, month, dayOfMonth ->
            calendarView.selectedDate=CalendarDay.from(year,month+1,dayOfMonth)
            calendarView.currentDate=calendarView.selectedDate
            Log.i("selected",calendarView.selectedDate.toString())
        },today.year,today.month-1,today.day)
        pickerBtn.setOnClickListener {
            datePickerDialog.show()
        }

        initData()
        initAdapter()

        calendarView.setOnDateChangedListener { widget, date, selected ->
        }
        calendarView.setOnMonthChangedListener { widget, date ->
            ymText.text=date.year.toString()+"."+date.month.toString()
        }
    }

    private fun initAdapter() {
        homeScheduleAdapter = HomeScheduleAdapter()
        binding.rvSchedule.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rvSchedule.adapter = homeScheduleAdapter
    }

    private fun initData() {
        val fbSchedule = Firebase.database.getReference("User").child(FBAuth.getUid()).child("schedule")
        fbSchedule.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                homeScheduleAdapter.schedules.clear()
                for (ds in snapshot.children) {
                    val start = ds.child("start").value.toString()
                    val end = ds.child("end").value.toString()
                    val title = ds.child("title").value.toString()
                    homeScheduleAdapter.schedules.add(Great.Schedule(title, start, end))
                }
                homeScheduleAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("database","database failed")
            }
        })
    }
}