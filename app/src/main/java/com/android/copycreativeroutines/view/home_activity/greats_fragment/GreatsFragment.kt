package com.android.copycreativeroutines.view.home_activity.greats_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.copycreativeroutines.databinding.FragmentGreatsBinding

class GreatsFragment : Fragment() {

    private lateinit var binding : FragmentGreatsBinding
    private lateinit var greatsRVAdapter : GreatsRVAdapter
//    var database = FirebaseDatabase.getInstance()
//    var table = database.getReference("Greats")
//
//    var great = table.child("1/name").get()


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