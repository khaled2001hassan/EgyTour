package com.example.graduationproject2.userUi.home

import android.content.Intent
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.home.AdminHomeActivity
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivityHomeBinding
import com.example.graduationproject2.userUi.home.ui.governorateFragment.GovernorateFragment
import com.example.graduationproject2.userUi.home.ui.makeFragment.MakeFragment
import com.example.graduationproject2.userUi.drawer.DrawerActivity
import com.example.graduationproject2.userUi.drawer.ui.favourite.FavouriteActivity
import com.example.graduationproject2.userUi.login.base.UserInfo
import com.example.graduationproject2.userUi.login.signIn.SignInActivity
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage


class HomeActivity : Base<HomeViewModel, ActivityHomeBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        var myObject = intent.getSerializableExtra("myObject") as UserInfo
        val myObject= UserInfo(id="K4wDlvDaxWT1BNpe5uZkrN23Ljk2", firstName = "khaled","hassan")

        showFragment(GovernorateFragment())
        initListener(myObject)
        initView(myObject)
    }

    private fun initView(myObject: UserInfo) {
        bind.FirstNameTextView.text=myObject.firstName
        bind.LastNameTextView.text=myObject.lastName
    }

    fun initListener(myObject:UserInfo){
        bind.Setting.setOnClickListener {
            val intent = Intent(this, DrawerActivity::class.java)
            intent.putExtra("massage","Setting")
            startActivity(intent)
        }
        bind.RequestToBeTourGuideSideTV.setOnClickListener {
            val intent = Intent(this, DrawerActivity::class.java)
            intent.putExtra("massage","RequestTo")
            intent.putExtra("myObject", myObject)
            startActivity(intent)
        }
        bind.AskForTourGuideSideTV.setOnClickListener {
            val intent = Intent(this, DrawerActivity::class.java)
            intent.putExtra("massage","AskFor")
            intent.putExtra("myObject", myObject)
            startActivity(intent)
        }
        bind.ProblemSideTV.setOnClickListener {
            val intent = Intent(this, DrawerActivity::class.java)
            intent.putExtra("massage","Problem")
            intent.putExtra("myObject", myObject)

            startActivity(intent)
        }
        bind.SideItem.setOnClickListener {
            bind.drawerLayout.open()
        }
        bind.BottomNav.setOnItemSelectedListener {
            if (it.itemId==R.id.gov){
                showFragment(GovernorateFragment())
            }else if (it.itemId==R.id.make_journey){
                showFragment(MakeFragment())

            }
            return@setOnItemSelectedListener true
        }
        bind.FavouritePlaceTextView.setOnClickListener {
            val intent = Intent(this, FavouriteActivity::class.java)
            startActivity(intent)
        }
        bind.LogoutTextView.setOnClickListener {
            val intent=Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }
    fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.HomeFragment, fragment).commit()
    }
}