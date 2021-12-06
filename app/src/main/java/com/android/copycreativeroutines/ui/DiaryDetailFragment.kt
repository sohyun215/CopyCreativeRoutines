package com.android.copycreativeroutines.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.FragmentDiaryDetailBinding


class DiaryDetailFragment(private val date:String, private val content:String) : Fragment() {
    private lateinit var binding:FragmentDiaryDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiaryDetailBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init(){
        binding.backBtn.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fc_home, ProfileFragment())
                .commit()
        }
        binding.diaryDate.text=date
        binding.diaryContent.text=content
    }

}