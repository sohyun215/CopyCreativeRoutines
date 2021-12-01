package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.GreatsRVAdapter
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.FragmentGreatsBinding

class GreatsFragment : Fragment() {

    private lateinit var binding: FragmentGreatsBinding
    private lateinit var greatsRVAdapter: GreatsRVAdapter
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
        greatsRVAdapter.setItemClickListener(object : GreatsRVAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                // great 정보 GreatDetailFragment에 넘겨주기
//               greatsRVAdapter.greatsList[position]

                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fc_home, GreatDetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        })
        binding.rvGreats.adapter = greatsRVAdapter

        val uri =
            "https://upload.wikimedia.org/wikipedia/commons/3/3e/Charles_Robert_Darwin_by_John_Collier.jpg"
        greatsRVAdapter.greatsList.addAll( // 위인 추가
            listOf(
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),

                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),

                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),
                Great(uri, "찰스다윈", ""),

                Great(uri, "찰스다윈", "")
            )
        )
    }
}