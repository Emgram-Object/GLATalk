package com.example.galtalk_project.Controller

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_my_info_change.*

class InfoChangeActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info_change)

        //기존 정보 서버에서 받아와서 표시
        //usermodel 머지 후


        modify_ok_btn.setOnClickListener{
            ButtonDisable(modify_ok_btn)
        }

    }


    val user_name = my_info_name_et.text.toString()
    val mobile_num = my_info_phone_et.text.toString()
    val user_EngName = my_info_english_name_et.text.toString()

    //팝업창 띄우기
    fun ButtonDisable(modify_ok_btn:Button){
        if(user_name.isBlank()||mobile_num.isBlank()||user_EngName.isBlank()){
            setBackgroundResurce(R.drawable.)
        }

    }


}