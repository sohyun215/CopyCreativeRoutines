package com.android.copycreativeroutines.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.ScheduleSelectAdapter
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.FragmentGreatDetailBinding
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

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
        Firebase.storage.reference.child(great.image).downloadUrl.addOnSuccessListener {
            Glide.with(this)
                .load(it.toString())
                .circleCrop()
                .into(binding.ivGreatImage)
        }
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

    private fun addSchedule() {
        val user= FirebaseDatabase.getInstance().getReference("User")
        for (index in scheduleSelectAdapter.checkedList) {
            user.child("schedule")
                .push()
                .setValue(great.schedule[index])
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