package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.MyPageRVAdapter
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.FragmentWriteDiaryBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class WriteDiaryFragment : Fragment() {

    private lateinit var binding: FragmentWriteDiaryBinding
    lateinit var rdb: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteDiaryBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    fun init(){
        rdb= FirebaseDatabase.getInstance().getReference("User")


        //오늘날짜 가져오기--> Firebase에서 key로 사용
        val currentTime=Calendar.getInstance().time
        val date=SimpleDateFormat("yyyy-MM-dd EE요일", Locale.getDefault()).format(currentTime)

        binding.saveBtn.setOnClickListener {
            val DiaryContents=binding.diaryEditText.text.toString()
            if(DiaryContents.isNotEmpty()){
                rdb.child("userDiary").push().setValue(User.Diary(date,DiaryContents))
                //파이어베이스에 데이터 저장하고 다시 마이페이지로 돌아가기?
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fc_home,ProfileFragment())
                    .commit()
            }


        }

    }

}