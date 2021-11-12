package com.android.copycreativeroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.android.copycreativeroutines.databinding.ActivityHomeBinding
import com.android.copycreativeroutines.home_activity.HomeViewPagerAdapter
import com.android.copycreativeroutines.home_activity.home_fragment.HomeFragment
import com.android.copycreativeroutines.home_activity.greats_fragment.GreatsFragment
import com.android.copycreativeroutines.home_activity.reward_fragment.RewardFragment
import com.android.copycreativeroutines.home_activity.profile_fragment.ProfileFragment
import com.android.copycreativeroutines.home_activity.reward_fragment.badge.BadgeFragment
import com.android.copycreativeroutines.home_activity.reward_fragment.ranking.RankingFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        init()
    }

    private fun init() {
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when(it.itemId){
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

    private fun transFragment(frgament : Int) {
        val transaction = supportFragmentManager.beginTransaction()

        when(frgament) {
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
}