package com.example.graduationproject2.base

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
@BindingAdapter("app:error")
fun textInputError(textInput: TextInputLayout,error:String?){
    textInput.error=error
}