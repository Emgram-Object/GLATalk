package com.example.glatalk_project.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.util.LocaleHelper
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*


class FirstLanguageActivity : AppCompatActivity() {

    //나중에 data class에 넣어주세요
    private val korLanguageCode: String = "ko"
    private val engLanguageCode: String = "en"
    private val jpnLanguageCode: String = "ja"
    private val chLanguageCode: String = "zh"


    override fun onCreate(savedInstanceState: Bundle?) {
        val locale = Locale.getDefault().getLanguage()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        defaultSelection(locale)

        next_btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            recreate()
            startActivity(intent)
            finish()

        }

        lang_korea.setOnClickListener {
            lang_korea.isSelected = true
            lang_english.isSelected = false
            lang_china.isSelected = false
            lang_japan.isSelected = false

            LocaleHelper.setLocale(this, korLanguageCode)
//            recreate()

        }

        lang_english.setOnClickListener {
            lang_english.isSelected = true
            lang_korea.isSelected = false
            lang_china.isSelected = false
            lang_japan.isSelected = false

            LocaleHelper.setLocale(this, engLanguageCode)
//            recreate()
        }

        lang_japan.setOnClickListener {
            lang_japan.isSelected = true
            lang_english.isSelected = false
            lang_china.isSelected = false
            lang_korea.isSelected = false

            LocaleHelper.setLocale(this, jpnLanguageCode)
//            recreate()
        }

        lang_china.setOnClickListener {
            lang_china.isSelected = true
            lang_english.isSelected = false
            lang_korea.isSelected = false
            lang_japan.isSelected = false

            LocaleHelper.setLocale(this, chLanguageCode)
//            recreate()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }

    fun defaultSelection(lang: String){
        when (lang){
            "ko" -> lang_korea.isSelected = true
            "en" -> lang_english.isSelected = true
            "zh" -> lang_china.isSelected = true
            "ja" -> lang_japan.isSelected =true
            else -> null
        }
    }
}
