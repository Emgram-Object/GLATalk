package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.constant.languageCode
import com.example.glatalk_project.util.LocaleHelper
import com.example.glatalk_project.view.Popup
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.ui_popup_custom.*

class SettingActivity: AppCompatActivity(){

    //얘좀 나중에 데이터 따로 모아주세요 ㅠㅠ
    private val korLanguageCode = languageCode.korLanguageCode
    private val engLanguageCode = languageCode.engLanguageCode
    private val jpnLanguageCode = languageCode.jpnLanguageCode
    private val chLanguageCode = languageCode.chLanguageCode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        C.TitleBackBtn.poptext = "바뀐 설정을 적용하려면 앱을 재시작 해야합니다.\n지금 재시작하겠습니까?"
        C.TitleBackBtn.CancelBack = true

        common_title_setting.setTitle(getString(R.string.title_setting))

        if(ProfileData.user_type.equals("tourist")){
            setting_line.visibility = View.GONE
            guide_chat_switch.visibility = View.GONE
        } else {
            setting_line.visibility = View.VISIBLE
            guide_chat_switch.visibility = View.VISIBLE
        }

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

    override fun onBackPressed() {
        val popUp = Popup(this)
        popUp.start("${C.TitleBackBtn.poptext}")
        val pop_up = popUp.popup
        val OKbtn =pop_up.fst_btn
        OKbtn.setOnClickListener {
            C.TitleBackBtn.CancelBack = false
            C.TitleBackBtn.closeOR = false
            pop_up.dismiss()
            finishAffinity()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            System.exit(0)
        }
        val cancelBTN = pop_up.snd_btn
        cancelBTN.setOnClickListener {
            C.TitleBackBtn.CancelBack = false
            C.TitleBackBtn.closeOR = false
            pop_up.dismiss()
            finish()
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