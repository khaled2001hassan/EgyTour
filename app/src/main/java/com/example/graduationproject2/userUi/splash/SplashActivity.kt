package com.example.graduationproject2.userUi.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.graduationproject2.R
import com.example.graduationproject2.userUi.login.signIn.SignInActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                val intent= Intent(this@SplashActivity,SignInActivity::class.java)
                startActivity(intent)
            }
            ,500
        )
    }
}