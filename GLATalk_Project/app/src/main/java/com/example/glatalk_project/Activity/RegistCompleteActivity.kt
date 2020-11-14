package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.activity_regist_complete.*

class RegistCompleteActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist_complete)

        back_login_btn.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
        }
    }
}