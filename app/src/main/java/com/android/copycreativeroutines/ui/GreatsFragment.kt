package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.GreatsRVAdapter
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.FragmentGreatsBinding

import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class GreatsFragment : Fragment() {

    private lateinit var binding: FragmentGreatsBinding
    private lateinit var greatsRVAdapter: GreatsRVAdapter


    var list = mutableListOf<Great>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGreatsBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        initData()
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
        greatsRVAdapter.greatsList = list
        binding.rvGreats.adapter = greatsRVAdapter
    }



    private fun initData() {
        val myRef = Firebase.database.getReference("Greats")

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val name = ds.child("name").value.toString()
                    val image: String = ds.child("image").value.toString()
                    val descript: String = ds.child("descript").value.toString()
                    val category: String = ds.child("category").value.toString()
//                    val schdeule = listOf<Great.Schedule>() // 추가
                    val schedList = mutableListOf<Great.Schedule>()
                    for (sched in ds.child("schedule").children) {
                        val startTime :String = sched.child("start").value.toString()
                        val endTime :String = sched.child("end").value.toString()
                        val title :String = sched.child("title").value.toString()
                        schedList.add(Great.Schedule(title, startTime,endTime))
                    }

                    list.add(Great(name,category,image,descript,schedList))
                }
                Log.d("database",list.toString())
                greatsRVAdapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                Log.e("database","database failed")
            }
        })
    }
}