package com.example.graduationproject2.adminUI.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.SecondActivity
import com.example.graduationproject2.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_admin_home)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_home)
        initListener()
    }

     fun initListener() {
         binding.UserToBeTourUide.setOnClickListener {
             val intent = Intent(this@AdminHomeActivity,SecondActivity::class.java)
             intent.putExtra("send","UserToBeTourUide")
             startActivity(intent)
         }
        binding.TourAskForTourGuide.setOnClickListener {
            val intent = Intent(this@AdminHomeActivity,SecondActivity::class.java)
            intent.putExtra("send","TourAskForTourGuide")
            startActivity(intent)
        }
         binding.Problems.setOnClickListener {
             val intent = Intent(this@AdminHomeActivity,SecondActivity::class.java)
             intent.putExtra("send","Problems")
             startActivity(intent)
         }
    }
}