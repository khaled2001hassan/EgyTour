package com.example.graduationproject2.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel() {
    var isLoadingLiveData=MutableLiveData<Boolean>()
    var dialogMessageLiveData=MutableLiveData<String>()

}