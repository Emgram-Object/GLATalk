package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.util.SharedPreferenceUtil

class FirstActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //FirstSetLanguage 값이 SharedPreference로 저장, 그 값의 유무를 판단

        //if, FirstSetLanguage 값 true -> go to Login Page
            //autoLogin의 유무를 boolean으로 저장 후(sharedPreference), 그 값으로 판단
        if(UserDAO.isAuto()==true){
            gotoMain()
        }else gotoLogin()
            //if, auto Login == true ,  go to Main
            //gotoMain
            //else, go to Login   (logout 시 autoLogin 값을 false로 초기화)
            //gotoLogin()

        //else, go to FirstSetLanguage
        //gotoFirstLang()
    }

    fun gotoMain(){
        val intentAct = Intent(this,MainActivity::class.java)
        startActivity(intentAct)
    }

    fun gotoLogin(){
        val intentAct = Intent(this,LoginActivity::class.java)
        startActivity(intentAct)
    }

    fun gotoFirstLang(){
        val intentAct = Intent(this,FirstLanguageActivity::class.java)
        startActivity(intentAct)
    }

}