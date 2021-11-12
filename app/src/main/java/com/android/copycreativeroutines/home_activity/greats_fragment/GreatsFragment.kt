package com.android.copycreativeroutines.home_activity.greats_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.FragmentGreatsBinding

class GreatsFragment : Fragment() {

    private lateinit var binding : FragmentGreatsBinding
    private lateinit var greatsRVAdapter : GreatsRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGreatsBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        initAdapter()
    }

    private fun initAdapter() {
        greatsRVAdapter = GreatsRVAdapter()
        binding.rvGreats.adapter = greatsRVAdapter

        greatsRVAdapter.greatsList.addAll( // 위인 추가
            listOf(
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                Great("",""),
                )
        )
    }
}