package com.example.glatalk_project.Activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class FirstActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //FirstSetLanguage 값이 SharedPreference로 저장, 그 값의 유무를 판단
        //if, FirstSetLanguage 값 true -> go to Login Page
            //autoLogin의 유무를 boolean으로 저장 후(sharedPreference), 그 값으로 판
            //if, auto Login == true ,  go to Main
            //else, go to Login   (logout 시 autoLogin 값을 false로 초기화)
        //else, go to FirstSetLanguage

    }
}