package com.example.graduationproject2.userUi.login.signIn

import com.example.graduationproject2.userUi.login.base.UserInfo

interface SigninNavigator {
    fun goTOHomeScreen(user:UserInfo)
    fun goTOHomeAdminScreen(user:UserInfo)

}