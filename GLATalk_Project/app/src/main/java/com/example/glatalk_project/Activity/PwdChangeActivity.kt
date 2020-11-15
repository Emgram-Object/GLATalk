package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.activity_pwd_change.*

class PwdChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pwd_change)

        pwd_change_ok.setOnClickListener {
            changePwdNetworking()
            finishPwdChange()
        }
    }





    fun changePwdNetworking(){
        // 통신부분 만든다음 여기로 연결
    }

    fun finishPwdChange() {
        val intent = Intent(this, MyInfoActivity::class.java)
        startActivity(intent)
    }
}