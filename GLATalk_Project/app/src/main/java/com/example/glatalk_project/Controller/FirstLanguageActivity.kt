package com.example.glatalk_project.Controller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.activity_language.*

class FirstLanguageActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        next_btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        lang_korea.setOnClickListener {  }

        lang_english.setOnClickListener {  }

        lang_japan.setOnClickListener {  }

        lang_china.setOnClickListener {  }
    }
}