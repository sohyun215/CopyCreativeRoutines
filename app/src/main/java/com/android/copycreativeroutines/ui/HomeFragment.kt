package com.android.copycreativeroutines.ui

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.HomeScheduleAdapter
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.FragmentHomeBinding
import com.android.copycreativeroutines.util.FBAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

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
        initView()
        initData(CalendarDay.today())
        initAdapter()

    }

    private fun initView() {
        val calendarView = binding.calendarView
        val ymText = binding.calendarYm
        val pickerBtn = binding.pickerBtn
        val today = CalendarDay.today() // yyyy-mm-dd

        calendarView.selectedDate = today
        calendarView.topbarVisible = false
        calendarView.isDynamicHeightEnabled = true
        ymText.text = today.year.toString() + "." + today.month.toString()

        val todayDecorator = object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay?): Boolean {
                return day?.equals(today)!!
            }

            override fun decorate(view: DayViewFacade?) {
                view?.addSpan(StyleSpan(Typeface.BOLD))
                view?.addSpan(RelativeSizeSpan(1.2f))
            }
        }
        val otherDayDecorator = object : DayViewDecorator {
            val drawable =
                ContextCompat.getDrawable(requireContext(), R.drawable.selector_other_day)

            override fun shouldDecorate(day: CalendarDay?): Boolean {
                return !(day?.equals(today)!!)
            }

            override fun decorate(view: DayViewFacade?) {
                view?.setSelectionDrawable(drawable!!)
                view?.addSpan(ForegroundColorSpan(Color.BLACK))
            }
        }
        calendarView.addDecorator(todayDecorator)
        calendarView.addDecorator(otherDayDecorator)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.DatePicker,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendarView.selectedDate = CalendarDay.from(year, month + 1, dayOfMonth)
                calendarView.currentDate = calendarView.selectedDate
                Log.i("selected", calendarView.selectedDate.toString())
            },
            today.year,
            today.month - 1,
            today.day
        )
        pickerBtn.setOnClickListener {
            datePickerDialog.show()
        }
        calendarView.setOnDateChangedListener { widget, date, selected ->
            //선택한 날짜에 해당하는 스케줄 받아오기
            Log.i("selected day",date.toString())
            initData(date)
        }
        calendarView.setOnMonthChangedListener { widget, date ->
            ymText.text = date.year.toString() + "." + date.month.toString()
        }

        // 나중에 삭제
        binding.btnTest.setOnClickListener {
            Firebase.database.getReference("User").child(FBAuth.getUid()).removeValue()
            Firebase.auth.currentUser?.delete()
            Firebase.auth.signOut()
            Firebase.auth.signInAnonymously().addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    initFirebaseData()
                    Toast.makeText(context, "계정 생성 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "계정 생성 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // 나중에 삭제
    private fun initFirebaseData() {
        val user = FirebaseDatabase.getInstance().getReference("User").child(FBAuth.getUid())
        val category = user.child("category")

        user.child("point").setValue(0)
        category.child("composer").setValue(0)
        category.child("writer").setValue(0)
        category.child("philosopher").setValue(0)
        category.child("politician").setValue(0)
        category.child("architect").setValue(0)
        category.child("biologist").setValue(0)
    }

    private fun initAdapter() {
        homeScheduleAdapter = HomeScheduleAdapter()
        binding.rvSchedule.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rvSchedule.adapter = homeScheduleAdapter
    }

    private fun initData(selectedDate:CalendarDay) {
        val fbSchedule =
            Firebase.database.getReference("User").child(FBAuth.getUid()).child("schedule").orderByChild("start")
        fbSchedule.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                homeScheduleAdapter.schedules.clear()
                for (ds in snapshot.children) {
                    val title = ds.key.toString()
                    val start = ds.child("start").value.toString()
                    val end = ds.child("end").value.toString()
                    val date = ds.child("date").value.toString()
                    val success = ds.child("success").value.toString()
                    val dateSplit=date.split("-")
                    if(dateSplit[0].toInt()==selectedDate.year &&
                        dateSplit[1].toInt()==selectedDate.month &&
                        dateSplit[2].toInt()==selectedDate.day){
                            homeScheduleAdapter.schedules.add(User.Schedule(title, date, start, end, success))
                    }
                }
                homeScheduleAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("database", "database failed")
            }
        })
    }
}