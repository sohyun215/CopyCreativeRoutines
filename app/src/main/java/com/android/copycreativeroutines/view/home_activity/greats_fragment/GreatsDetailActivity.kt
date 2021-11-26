package com.android.copycreativeroutines.view.home_activity.greats_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.ActivityGreatsDetailBinding

class GreatsDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGreatsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_greats_detail)
        init()
    }

    private fun init() {
        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")

        // 추가
    }
}