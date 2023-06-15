package com.example.graduationproject2.userUi.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.ActivityDrawerBinding
import com.example.graduationproject2.userUi.drawer.ui.guide.AskToBeTourGuideFragment
import com.example.graduationproject2.userUi.drawer.ui.problem.WriteProblemFragment
import com.example.graduationproject2.userUi.drawer.ui.setting.SettingFragment
import com.example.graduationproject2.userUi.drawer.ui.user.UsetAskForGuideFragment

class DrawerActivity : AppCompatActivity() {
    lateinit var bind : ActivityDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=DataBindingUtil.setContentView(this,R.layout.activity_drawer)
        val send = intent.getStringExtra("massage")
        pushFragment(send!!)

    }

    private fun pushFragment(send: String) {
        if (send=="Setting"){
            showFragment(SettingFragment())
        }else  if (send=="RequestTo"){
            showFragment(AskToBeTourGuideFragment())
        }else  if (send=="Problem"){
            showFragment(WriteProblemFragment())
        }else  if (send=="AskFor"){
            showFragment(UsetAskForGuideFragment())
        }
    }

    fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.DrawerFragment, fragment).commit()
    }
}