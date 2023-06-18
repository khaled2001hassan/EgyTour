package com.example.graduationproject2.userUi.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.base.rooms.MyDataBase
import com.example.graduationproject2.databinding.ActivityDetailsBinding
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage

class DetailsActivity : AppCompatActivity() {
    var check = false
    lateinit var bindind: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = DataBindingUtil.setContentView(this, R.layout.activity_details)
//        setContentView(R.layout.activity_details)
        val myObject = intent.getSerializableExtra("myObject") as PlaceWithImage
        bindind.test = myObject

        MyDataBase.getInstance(context = this@DetailsActivity).getPlaceDao().getPlace().forEach {
            if (myObject == it) {
                check = true
                return@forEach
            }
        }
        if (check) {
            bindind.favouriteImageView.setImageResource(R.drawable.ic_favorite)
        } else {
            bindind.favouriteImageView.setImageResource(R.drawable.ic_not_favorite)
        }

        bindind.favouriteImageView.setOnClickListener {
            Log.e("khaled", check.toString())
            if (!check) {
                MyDataBase.getInstance(this).getPlaceDao().addPlace(myObject)
                bindind.favouriteImageView.setImageResource(R.drawable.ic_favorite)
                check = false

            } else if (check) {
                MyDataBase.getInstance(this).getPlaceDao().deletePlace(myObject)
                bindind.favouriteImageView.setImageResource(R.drawable.ic_not_favorite)
                check = true
            }
        }

    }
}