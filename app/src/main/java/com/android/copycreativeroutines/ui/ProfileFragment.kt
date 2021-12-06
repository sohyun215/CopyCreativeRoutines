package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.MyPageRVAdapter
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.FragmentProfileBinding
import com.android.copycreativeroutines.util.FBAuth
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
        myPageRVAdapter=MyPageRVAdapter(diaryList)
        binding.rvDiary.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        myPageRVAdapter.itemClickListener=object :MyPageRVAdapter.OnItemClickListener{
            override fun OnItemClick(holder:MyPageRVAdapter.ViewHolder,view: View, position: Int) {
                val diary_content=holder.contentView.text.toString()
                val diary_date=holder.dateView.text.toString()
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fc_home, DiaryDetailFragment(diary_date,diary_content))
                    .addToBackStack(null)
                    .commit()
            }

        }
        binding.rvDiary.adapter=myPageRVAdapter

        binding.userId.text = FBAuth.getUid().chunked(10)[0]

        rdb= FirebaseDatabase.getInstance().getReference("User").child(FBAuth.getUid()).child("diary")
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
        binding.writeDiaryBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fc_home,WriteDiaryFragment())
                .addToBackStack(null)
                .commit()
        }

    }

}