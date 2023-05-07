package com.example.graduationproject2.ui.home

import android.content.Intent
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivityHomeBinding
import com.example.graduationproject2.ui.home.ui.governorateFragment.GovernorateFragment
import com.example.graduationproject2.ui.home.ui.makeFragment.MakeFragment
import com.example.graduationproject2.ui.setting.SettingActivity


class HomeActivity : Base<HomeViewModel, ActivityHomeBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//       val drawerLayout: DrawerLayout = binding.drawerLayout
        bind.Setting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        bind.SideItem.setOnClickListener {
            bind.drawerLayout.open()
        }
        showFrogment(GovernorateFragment())
        bind.BottomNav.setOnItemSelectedListener {
            if (it.itemId==R.id.gov){
                showFrogment(GovernorateFragment())
            }else if (it.itemId==R.id.make_journey){
                showFrogment(MakeFragment())

            }
            return@setOnItemSelectedListener true
        }

    }
    fun showFrogment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.HomeFragment, fragment).commit()
    }
}