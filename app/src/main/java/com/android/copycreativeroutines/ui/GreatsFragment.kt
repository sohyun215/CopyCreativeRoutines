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
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class GreatsFragment : Fragment() {

    private lateinit var binding: FragmentGreatsBinding
    private lateinit var greatsRVAdapter: GreatsRVAdapter
    lateinit var rdb:DatabaseReference


    var name :String = "blank"
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
        Log.e("database",list.toString())
        initAdapter()
    }

    private fun initData() {
        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("Greats")

        rdb = FirebaseDatabase.getInstance().getReference("Greats/1")
        Log.e("database",rdb.toString())
        rdb.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    Log.e("database",ds.toString())
                    var name : String = ds.child("name").value as String
                    var image: String = ds.child("image").value as String
                    var descript: String = ds.child("descript").value as String
                    var category: String = ds.child("category").value as String

                    list.add(Great(name,category,image,descript,listOf(Great.Schedule("","",""))))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        myRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("database","initiated2")
                for (ds in snapshot.children) {
                    Log.e("database",ds.toString())
                    var name : String = ds.child("name").value as String
                    var image: String = ds.child("image").value as String
                    var descript: String = ds.child("descript").value as String
                    var category: String = ds.child("category").value as String

                    list.add(Great(name,category,image,descript,listOf(Great.Schedule("","",""))))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("database","database failed")
            }

        })
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

        initData()

        val uri =
            "https://upload.wikimedia.org/wikipedia/commons/3/3e/Charles_Robert_Darwin_by_John_Collier.jpg"
        greatsRVAdapter.greatsList.addAll( // 위인 추가
            listOf(
//                Great(myRef.child("1/names").value.toString(),"","","",listOf(Great.Schedule("","",""))),
//                Great(name, "", uri, "", listOf(Great.Schedule("","",""))),
                Great("찰스다윈", "", uri, "", listOf(Great.Schedule("","",""))),
                Great("찰스다윈", "", uri, "", listOf(Great.Schedule("","",""))),
                Great("찰스다윈", "", uri, "", listOf(Great.Schedule("","",""))),
                Great("찰스다윈", "", uri, "", listOf(Great.Schedule("","",""))),

            )
        )
    }
}