package com.android.copycreativeroutines.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.ScheduleSelectAdapter
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.FragmentGreatDetailBinding
import com.android.copycreativeroutines.util.FBAuth
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class GreatDetailFragment(private val great: Great) : Fragment() {
    private lateinit var binding: FragmentGreatDetailBinding
    private lateinit var scheduleSelectAdapter: ScheduleSelectAdapter
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
        // Firebase Storage에서 이미지를 갑져오는 방식이 너무 오래걸려서 프로젝트 내부 사진으로부터 가져옴
        val imageName = great.image.chunked(great.image.length - 4)[0]
        val image =
            this.context?.resources?.getIdentifier(imageName, "drawable", this.context?.packageName)
        Glide.with(this)
            .load(image)
            .circleCrop()
            .into(binding.ivGreatImage)

        // Firbase Storage에서 이미지를 가져오는 방식
//        Firebase.storage.reference.child(great.image).downloadUrl.addOnSuccessListener {
//            Glide.with(this)
//                .load(it.toString())
//                .circleCrop()
//                .into(binding.ivGreatImage)
//        }
        binding.tvGreatName.text = great.name
        binding.tvGreatInfo.text = great.descript
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
            if (scheduleSelectAdapter.checkedList.size > 0) {
                addSchedule()
                listener?.onFragmentInteraction()
            } else {
                Toast.makeText(context, "일과를 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSchedule(index: Int) {
        val currentDate =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().time)
        val schedule = FirebaseDatabase.getInstance().getReference("User").child(FBAuth.getUid())
            .child("schedule").child(currentDate).push()

        schedule.child("title").setValue(great.schedule[index].title)
        schedule.child("start").setValue(great.schedule[index].start)
        schedule.child("end").setValue(great.schedule[index].end)
        schedule.child("category").setValue(great.category)
        schedule.child("success").setValue(false)
    }

    private fun addSchedule() {
        val currentDate =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().time)

        for (index in scheduleSelectAdapter.checkedList) {
            var flag = false
            val title = great.schedule[index].title
            val schedules =
                FirebaseDatabase.getInstance()
                    .getReference("User")
                    .child(FBAuth.getUid())
                    .child("schedule")
                    .child(currentDate)

            val greatStart = LocalTime.parse(great.schedule[index].start)
            val greatEnd = LocalTime.parse(great.schedule[index].end)
            schedules.orderByChild("start").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (ds in snapshot.children) {
                        val start = LocalTime.parse(ds.child("start").value.toString())
                        val end = LocalTime.parse(ds.child("end").value.toString())
                        if((start.compareTo(greatStart) <= 0 && greatStart.compareTo(end) < 0) ||
                            (start.compareTo(greatEnd) < 0 && greatEnd.compareTo(end) <= 0)) {
                            flag = true
                        } else {
                            schedules.orderByChild("title").equalTo(title)
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            flag = true
                                        }
                                    }

                                    override fun onCancelled(databaseError: DatabaseError) {
                                        Log.e("database", "database failed")
                                    }
                                })
                        }
                    }
                    if(!flag)
                        setSchedule(index)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("database", "database failed")
                }
            })
        }
    }

    private fun initAdapter() {
        scheduleSelectAdapter = ScheduleSelectAdapter()
        scheduleSelectAdapter.sheduleList.addAll(great.schedule)
        binding.rvScheduleList.adapter = scheduleSelectAdapter
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }
}