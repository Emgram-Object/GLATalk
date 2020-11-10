package com.example.glatalk_project.Controller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import android.widget.EditText
import android.widget.Button

class MyInfoActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        //common title 수정버튼 누르면 넘어감


        //기존 서버에서 받아와서 editText에 표시

       //비밀번호 변경
        val btn_ChangePw = findViewById<Button>(R.id.my_info_pwd_change_btn)
        btn_ChangePw.setOnClickListener{
            //startActivity(Intent(this, PwdChangeActivity::class.java))
        }

    }
}