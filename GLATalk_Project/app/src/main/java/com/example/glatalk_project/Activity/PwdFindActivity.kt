package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.activity_find_pwd.*

class PwdFindActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pwd)

        find_pwd_send.setOnClickListener {

        }

        find_pwd_back_btn.setOnClickListener {

        }
    }
    fun goBack(){
        val intentAct = Intent(this, LoginActivity::class.java)
        startActivity(intentAct)
        finish()
    }





}