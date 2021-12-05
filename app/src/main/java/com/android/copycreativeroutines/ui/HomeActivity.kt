package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.ActivityHomeBinding
import com.android.copycreativeroutines.util.FBAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity(), GreatDetailFragment.OnFragmentInteractionListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        init()
    }

    private fun init() {
        initLogin()
        initBottomNavigation()
    }
    private fun initLogin() {
        auth = Firebase.auth
        auth.signInAnonymously().addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(baseContext, FBAuth.getUid(), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun initBottomNavigation() {
        transFragment(FIRST_FRAGMENT)
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    transFragment(FIRST_FRAGMENT)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_greats -> {
                    transFragment(SECOND_FRAGMENT)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_reward -> {
                    transFragment(THIRD_FRAGMENT)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    transFragment(FOURTH_FRAGMENT)
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    private fun transFragment(frgament: Int) {
        supportFragmentManager.popBackStack()

        val transaction = supportFragmentManager.beginTransaction()

        when (frgament) {
            FIRST_FRAGMENT -> {
                val homeFragment = HomeFragment()
                transaction.replace(R.id.fc_home, homeFragment).commit()

            }
            SECOND_FRAGMENT -> {
                val greatsFragment = GreatsFragment()
                transaction.replace(R.id.fc_home, greatsFragment).commit()
            }
            THIRD_FRAGMENT -> {
                val rewardFragment = RewardFragment()
                transaction.replace(R.id.fc_home, rewardFragment).commit()
            }
            FOURTH_FRAGMENT -> {
                val profileFragment = ProfileFragment()
                transaction.replace(R.id.fc_home, profileFragment).commit()
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
        const val FOURTH_FRAGMENT = 3
    }

    override fun onFragmentInteraction() {
        binding.bnvHome.selectedItemId = R.id.menu_home
    }
}