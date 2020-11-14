package com.example.glatalk_project.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.core.helper.LocaleHelper
import kotlinx.android.synthetic.main.activity_language.*

class FirstLanguageActivity : AppCompatActivity() {

    //나중에 data class에 넣어주세요
    private val korLanguageCode: String = "ko"
    private val engLanguageCode: String = "en"
    private val jpnLanguageCode: String = "ja"
    private val chLanguageCode: String = "zh"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        next_btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            recreate()
            finish()
        }

        lang_korea.setOnClickListener {
            lang_korea.setSelected(!lang_korea.isSelected())
            lang_english.isSelected = false
            lang_china.isSelected = false
            lang_japan.isSelected = false

            LocaleHelper.setLocale(this, korLanguageCode)

        }

        lang_english.setOnClickListener {
            lang_english.setSelected(!lang_korea.isSelected())
            lang_korea.isSelected = false
            lang_china.isSelected = false
            lang_japan.isSelected = false

            LocaleHelper.setLocale(this, engLanguageCode)
        }

        lang_japan.setOnClickListener {
            lang_japan.setSelected(!lang_korea.isSelected())
            lang_english.isSelected = false
            lang_china.isSelected = false
            lang_korea.isSelected = false

            LocaleHelper.setLocale(this, jpnLanguageCode)
        }

        lang_china.setOnClickListener {
            lang_china.setSelected(!lang_korea.isSelected())
            lang_english.isSelected = false
            lang_korea.isSelected = false
            lang_japan.isSelected = false

            LocaleHelper.setLocale(this, chLanguageCode)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }
}