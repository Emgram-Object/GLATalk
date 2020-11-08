package com.example.galtalk_project.Controller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import android.widget.EditText
import android.widget.Button

class TouristInfoChangeActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        //기존 정보 서버에서 받아와서 표시

        val user_name = findViewById<EditText>(R.id.my_info_name_et).text.toString()
        val mobile_num = findViewById<EditText>(R.id.my_info_phone_et).text.toString()
        val user_email = findViewById<EditText>(R.id.my_info_email_et).text.toString()

       // user_name.setText("")
        /*
        val user_name = my_info_name_et.text.toString()
        val mobile_num = Phone.text.toString()
        val user_email = Email.text
        */
        val btn_Scene = findViewById<Button>(R.id.my_info_pwd_change_btn)

        //패스워드 바꾸는 Activity로 넘어가기
        btn_Scene.setOnClickListener{
            //startActivity(Intent(this, PwdChangeActivity::class.java))
            //비밀번호 변경 액티비티 만들면 넣어주기
            finish()
        }
    }



    //후에 버튼 누르면 서버로 넘겨주기

}