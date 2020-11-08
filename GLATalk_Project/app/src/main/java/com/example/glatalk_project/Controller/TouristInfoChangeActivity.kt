package com.example.galtalk_project.Controller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import android.widget.EditText


class TouristInfoChangeActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        //기존 정보 받아와서 표시하기 //서버에서 받아오기

        val Name = findViewById(R.id.my_info_name_et) as EditText
        val Phone = findViewById<EditText>(R.id.my_info_phone_et)
        val Email = findViewById<EditText>(R.id.my_info_email_et)

       // user_name.setText("")

        val user_name =  Name.text
        val mobile_num = Phone.text
        val user_email = Email.text



    }
}