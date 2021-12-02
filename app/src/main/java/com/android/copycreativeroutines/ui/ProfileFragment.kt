package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.GreatsRVAdapter
import com.android.copycreativeroutines.adapter.MyPageRVAdapter
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.FragmentGreatsBinding
import com.android.copycreativeroutines.databinding.FragmentProfileBinding
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var myPageRVAdapter: MyPageRVAdapter
    var diaryList= mutableListOf<User.Diary>()
    lateinit var rdb:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    fun init(){
        //UID 현재 사용자 아이디로 text설정
        // binding.userId.text=
        myPageRVAdapter=MyPageRVAdapter(diaryList)
        binding.rvDiary.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rvDiary.adapter=myPageRVAdapter

        rdb= FirebaseDatabase.getInstance().getReference("User/userDiary")
        rdb.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                myPageRVAdapter.diaryList.clear()
                for (ds in snapshot.children) {
                    //Log.i("database",ds.toString())
                    val date=ds.child("date").value.toString()
                    val content=ds.child("content").value.toString()
                    diaryList.add(User.Diary(date,content))
                    }
            myPageRVAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("warning", "loadPost:onCancelled")
            }

        })
//        myPageRVAdapter.diaryList.addAll(
//
//                listOf(
//                    User.Diary("2021-12-02 목요일","일기내용1"),
//                    User.Diary("2021-12-03 금요일","일기내용2"),
//                    User.Diary("2021-12-04 토요일","일기내용3")
//                )
//
//            )
        binding.writeDiaryBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fc_home,WriteDiaryFragment())
                .addToBackStack(null)
                .commit()
        }

    }

}