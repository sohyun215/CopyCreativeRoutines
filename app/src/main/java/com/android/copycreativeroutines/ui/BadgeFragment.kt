package com.android.copycreativeroutines.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.copycreativeroutines.R
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
        val imageArray=requireContext().resources.obtainTypedArray(R.array.badge_image)
        val titleArray=requireContext().resources.getStringArray(R.array.badge_title)
        val positionArray=requireContext().resources.getStringArray(R.array.badge)
        for (i in 0 until imageArray.length()){
            badgeRVAdapter.badgeImageList.add(mutableMapOf(positionArray[i] to mutableMapOf(titleArray[i] to imageArray.getResourceId(i,-1))))
        }
        binding.rvBadge.adapter=badgeRVAdapter

    }

    private fun initData(){
        val fb_category = FirebaseDatabase.getInstance().getReference("User").child(FBAuth.getUid()).child("category")
        fb_category.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                badgeRVAdapter.badgeList.clear()
                for (ds in snapshot.children) {
                    val category = ds.key.toString()
                    val achieve= ds.value as Long  // 스케줄 달성 횟수
                    val map= mutableMapOf(category to achieve)
                    badgeRVAdapter.badgeList.add(map)
                    Log.i("b",badgeRVAdapter.badgeList.toString())
                }
                badgeRVAdapter.notifyDataSetChanged()

            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("database", "database failed")
            }
        })
    }
}