package com.example.glatalk_project.Controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import android.widget.EditText
import android.widget.Button
import com.example.glatalk_project.Activity.PwdFindActivity
import kotlinx.android.synthetic.main.activity_my_info.*

class MyInfoActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        //common title 수정버튼 누르면 넘어감


        //기존 서버에서 받아와서 editText에 표시

       //비밀번호 변경
        my_info_pwd_change_btn.setOnClickListener{
            Pwd_Change()
        }
    }

    private fun Pwd_Change(){
        val intent = Intent(this, PwdFindActivity::class.java)
        startActivity(intent)
    }
}