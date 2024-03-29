package com.example.graduationproject2.userUi.region.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun setImage(imageView: ImageView,imadeid:String?){
    if (imadeid==null) return
    else {
        Glide.with(imageView.context)
            .load(imadeid)
            .into(imageView)
    }
}