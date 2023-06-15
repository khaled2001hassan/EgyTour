package com.example.graduationproject2.adminUI.secondScreen
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.askTour.AskForTourGuideFragment
import com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide.BeTourGuideFragment
import com.example.graduationproject2.adminUI.secondScreen.fragments.problem.ProblemsFragment
import com.example.graduationproject2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding:ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_second)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_second)
        val send = intent.getStringExtra("send")
        pushFragment(send!!)

    }

    private fun pushFragment(send: String) {
        if (send=="UserToBeTourUide"){
            showFragment(BeTourGuideFragment())
        }else  if (send=="TourAskForTourGuide"){
            showFragment(AskForTourGuideFragment())
        }else  if (send=="Problems"){
            showFragment(ProblemsFragment())
        }
    }

    fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.SecondScreenFragment, fragment).commit()
    }
}