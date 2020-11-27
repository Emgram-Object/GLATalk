package com.example.glatalk_project.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.MoveActivity
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.constant.languageCode
import com.example.glatalk_project.util.LocaleHelper

class SplashActivity : AppCompatActivity(), MoveActivity {
    var userDao = UserDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //FirstSetLanguage 값이 SharedPreference로 저장, 그 값의 유무를 판단

        //if, FirstSetLanguage 값 true -> go to Login Page
        if (LocaleHelper.haveLangValue()) {
            if (UserDAO.isAutoLogin()) {
                TokenData.loginToken = userDao.getLoginToken()
                Log.d("autoLogin", "${TokenData.loginToken}")
                MyDao.getInfo(LoginActivity())
                gotoMain()
            } else gotoLogin()
        }else{
            gotoFirstLang()
        }
    }


    fun gotoMain() {
        val intentAct = Intent(this, MainActivity::class.java)
        startActivity(intentAct)
        finish()
    }

    fun gotoLogin() {
        val intentAct = Intent(this, LoginActivity::class.java)
        startActivity(intentAct)
        finish()
    }

    fun gotoFirstLang() {
        val intentAct = Intent(this, FirstLanguageActivity::class.java)
        startActivity(intentAct)
        finish()
    }

    override fun move() {

    }

}