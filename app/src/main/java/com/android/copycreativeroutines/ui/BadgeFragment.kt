package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.copycreativeroutines.adapter.BadgeRVAdapter
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.FragmentBadgeBinding
import com.android.copycreativeroutines.util.FBAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BadgeFragment : Fragment() {

    private lateinit var binding : FragmentBadgeBinding
    private lateinit var badgeRVAdapter: BadgeRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBadgeBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init(){
        initData()
        initAdapter()
    }

    private fun initAdapter(){
        badgeRVAdapter= BadgeRVAdapter()
        binding.rvBadge.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rvBadge.adapter=badgeRVAdapter

    }

    private fun initData(){
        val fb_category = FirebaseDatabase.getInstance().getReference("User").child(FBAuth.getUid()).child("category")
        fb_category.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                badgeRVAdapter.badgeList.clear()
                for (ds in snapshot.children) {
                    val category = ds.key.toString()
                    val achieve= ds.value as Long
                    val map= mutableMapOf(category to achieve)
                    badgeRVAdapter.badgeList.add(map)
                }
                badgeRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("database", "database failed")
            }
        })

    }
}