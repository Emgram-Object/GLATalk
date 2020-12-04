package com.example.glatalk_project.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.constant.C.Preference.SELECTED_LANGUAGE
import com.example.glatalk_project.constant.languageCode
import com.example.glatalk_project.constant.languageCode.chLanguageCode
import com.example.glatalk_project.constant.languageCode.engLanguageCode
import com.example.glatalk_project.constant.languageCode.jpnLanguageCode
import com.example.glatalk_project.constant.languageCode.korLanguageCode
import com.example.glatalk_project.util.LocaleHelper
import com.example.glatalk_project.util.SharedPreferenceUtil
import com.example.glatalk_project.view.Popup
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.ui_popup_custom.*

class SettingActivity: AppCompatActivity(){
    private lateinit var newlang:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        C.TitleBackBtn.poptext = getString(R.string.popup_setting)
        C.TitleBackBtn.CancelBack = true

        common_title_setting.setTitle(getString(R.string.title_setting))

        if(ProfileData.user_type.equals("tourist")){
            setting_line.visibility = View.GONE
            guide_chat_switch.visibility = View.GONE
        } else {
            setting_line.visibility = View.VISIBLE
            guide_chat_switch.visibility = View.VISIBLE
        }

        val currentlang = LocaleHelper.getLanguage(this)
        defaultSelection(currentlang)

        lang_korea_rb.setOnClickListener {
            lang_english_rb.isChecked = false
            lang_china_rb.isChecked = false
            lang_japan_rb.isChecked = false

            newlang = korLanguageCode
        }

        lang_english_rb.setOnClickListener {
            lang_korea_rb.isChecked = false
            lang_china_rb.isChecked = false
            lang_japan_rb.isChecked = false

            newlang = engLanguageCode
        }

        lang_china_rb.setOnClickListener {
            lang_english_rb.isChecked = false
            lang_korea_rb.isChecked = false
            lang_japan_rb.isChecked = false

            newlang= chLanguageCode
        }

        lang_japan_rb.setOnClickListener {
            lang_english_rb.isChecked = false
            lang_china_rb.isChecked = false
            lang_korea_rb.isChecked = false

            newlang= jpnLanguageCode
        }
    }

    override fun onBackPressed() {

            val popUp = Popup(this)
            popUp.start("${C.TitleBackBtn.poptext}")
            val pop_up = popUp.popup
            val OKbtn = pop_up.fst_btn
            OKbtn.setOnClickListener {
                LocaleHelper.setLocale(this, newlang)

                Thread.sleep(500)// Locale적용되기전에 꺼버려서 시간벌려고 넣었어요 ㅋㅋㄹㅃㅃ

                C.TitleBackBtn.CancelBack = false
                C.TitleBackBtn.closeOR = false
                pop_up.dismiss()
                finishAffinity()
                val intent = Intent(this, SplashActivity::class.java)
//                Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(intent)
                System.exit(0)
            }
            val cancelBTN = pop_up.snd_btn
            cancelBTN.setOnClickListener {
                C.TitleBackBtn.CancelBack = false
                C.TitleBackBtn.closeOR = false
                pop_up.dismiss()
                finish()
                C.TitleBackBtn.poptext = getString(R.string.popup_quit)
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