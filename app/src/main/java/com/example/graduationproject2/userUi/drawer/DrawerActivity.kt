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
import com.example.graduationproject2.userUi.login.base.UserInfo
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage

class DrawerActivity : AppCompatActivity() {
    lateinit var bind : ActivityDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=DataBindingUtil.setContentView(this,R.layout.activity_drawer)
        val myObject = intent.getSerializableExtra("myObject") as UserInfo
        val send = intent.getStringExtra("massage")
        pushFragment(send!!,myObject)

    }

    private fun pushFragment(send: String,myObject:UserInfo,) {
        if (send=="Setting"){
            showFragment(SettingFragment())
        }else  if (send=="RequestTo"){
            showFragment(AskToBeTourGuideFragment(myObject))
        }else  if (send=="Problem"){
            showFragment(WriteProblemFragment(myObject))
        }else  if (send=="AskFor"){
            showFragment(UsetAskForGuideFragment(myObject))
        }
    }

    fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.DrawerFragment, fragment).commit()
    }
}