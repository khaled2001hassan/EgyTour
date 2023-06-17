package com.example.graduationproject2.userUi.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.ActivityDetailsBinding
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage

class DetailsActivity : AppCompatActivity() {
    lateinit var bindind:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind=DataBindingUtil.setContentView(this,R.layout.activity_details)
//        setContentView(R.layout.activity_details)
        val myObject = intent.getSerializableExtra("myObject") as PlaceWithImage
      bindind.test=myObject

    }
}