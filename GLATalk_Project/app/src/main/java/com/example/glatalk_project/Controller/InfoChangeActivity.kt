package com.example.galtalk_project.Controller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import android.widget.EditText
import android.widget.Button

class InfoChangeActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info_change)

        //기존 정보 서버에서 받아와서 표시

        val user_name = findViewById<EditText>(R.id.my_info_name_et).text.toString()
        val mobile_num = findViewById<EditText>(R.id.my_info_phone_et).text.toString()
        val user_email = findViewById<EditText>(R.id.my_info_email_et).text.toString()

       // user_name.setText("")
        val btn_SaveInfo = findViewById<Button>(R.id.modify_ok_btn)
        btn_SaveInfo.setOnClickListener{

        }
    }



    //후에 버튼 누르면 서버로 넘겨주기

}