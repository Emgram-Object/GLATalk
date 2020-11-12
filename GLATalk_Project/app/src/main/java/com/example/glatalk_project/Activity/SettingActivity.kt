package com.example.glatalk_project.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.core.helper.LocaleHelper
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: AppCompatActivity(){

    //얘좀 나중에 데이터 따로 모아주세요 ㅠㅠ
    private val korLanguageCode: String = "ko"
    private val engLanguageCode: String = "en"
    private val jpnLanguageCode: String = "ja"
    private val chLanguageCode: String = "zh"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val lang = LocaleHelper.getLanguage(this)

        defaultSelection(lang)

        lang_korea_rb.setOnClickListener {
            lang_english_rb.isChecked = false
            lang_china_rb.isChecked = false
            lang_japan_rb.isChecked = false

            LocaleHelper.setLocale(this, korLanguageCode)
        }

        lang_english_rb.setOnClickListener {
            lang_korea_rb.isChecked = false
            lang_china_rb.isChecked = false
            lang_japan_rb.isChecked = false

            LocaleHelper.setLocale(this, engLanguageCode)
        }

        lang_china_rb.setOnClickListener {
            lang_english_rb.isChecked = false
            lang_korea_rb.isChecked = false
            lang_japan_rb.isChecked = false

            LocaleHelper.setLocale(this, chLanguageCode)
        }

        lang_japan_rb.setOnClickListener {
            lang_english_rb.isChecked = false
            lang_china_rb.isChecked = false
            lang_korea_rb.isChecked = false

            LocaleHelper.setLocale(this, jpnLanguageCode)
        }
    }

    fun defaultSelection(lang: String){
        when (lang){
            "ko" -> lang_korea_rb.isChecked = true
            "en" -> lang_english_rb.isChecked = true
            "zh" -> lang_china_rb.isChecked = true
            "ja" -> lang_japan_rb.isChecked =true
            else -> null
        }
    }

}