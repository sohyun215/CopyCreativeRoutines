package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.copycreativeroutines.adapter.RankingRVAdapter
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.FragmentRankingBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RankingFragment : Fragment() {

    private lateinit var binding: FragmentRankingBinding
    private lateinit var rankingRVAdapter: RankingRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        initAdapter()
    }

    private fun initAdapter() {
        rankingRVAdapter = RankingRVAdapter()
        binding.rvRanking.adapter = rankingRVAdapter
        initData()
    }

    private fun initData() {

        val user = Firebase.database.getReference("User")

        user.orderByChild("point").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                rankingRVAdapter.userList.clear()
                for (ds in snapshot.children) {
                    val id = ds.key.toString().chunked(10)[0]
                    val point = ds.child("point").value.toString().toInt()
                    rankingRVAdapter.userList.add(User(id, point))
                }
                rankingRVAdapter.userList.reverse()
                rankingRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("database", "database failed")
            }
        })
    }

}