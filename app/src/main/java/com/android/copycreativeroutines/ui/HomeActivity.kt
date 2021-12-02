package com.android.copycreativeroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.adapter.GreatsRVAdapter
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.ActivityHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity(), GreatDetailFragment.OnFragmentInteractionListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var greatsRVAdapter: GreatsRVAdapter

    val database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Greats")
    var list = mutableListOf<Great>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        init()
        initData()
    }

    private fun initData(){
        greatsRVAdapter = GreatsRVAdapter()
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    Log.e("database",ds.toString())
                    val name : String = ds.child("name").value as String
                    val image: String = ds.child("image").value as String
                    val descript: String = ds.child("descript").value as String
                    val category: String = ds.child("category").value as String
                    list.add(Great(name,category,image,descript,listOf(Great.Schedule("","",""))))
                }
                greatsRVAdapter.greatsList = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("database","onCancelled")
            }

        })
    }

    private fun init() {
        initBottomNavigation()
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