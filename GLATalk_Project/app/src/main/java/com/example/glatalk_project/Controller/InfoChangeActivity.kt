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

        val user_name = my_info_name_et.text.toString()
        val mobile_num = my_info_phone_et.text.toString()
        val user_EngName = my_info_english_name_et.text.toString()

        modify_ok_btn.setOnClickListener{

        }

        //입력 비어있으면 toast? 다른 방법 없나
        fun ButtonDisable(modify_ok_btn:Button){
            if(TextUtils.isEmpty(user_name)){
                modify_ok_btn?.isEnabled = false
                Toast.makeText(this, "이름이 비어있습니다.", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(mobile_num)){
                modify_ok_btn?.isEnabled = false
                Toast.makeText(this, "전화번호가 비어있습니다.", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(user_EngName)){
                modify_ok_btn?.isEnabled = false
                Toast.makeText(this, "이메일이 비어있습니다.", Toast.LENGTH_SHORT).show()
            }

        }
    }


    //후에 버튼 누르면 서버로 넘겨주기

}