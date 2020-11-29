package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import kotlinx.android.synthetic.main.activity_my_info.*
import kotlinx.android.synthetic.main.activity_my_info_change.*

class MyInfoActivity : AppCompatActivity() {

    var myDao = MyDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        common_title_my_info.setTitle(getString(R.string.title_myinfo))
        common_title_my_info.setModifyBtn()

        MyDao.getInfo(null)

        setTexts()
        Log.d("myInfo_token", "${TokenData.loginToken}")
        my_info_pwd_change_btn.setOnClickListener {
            Pwd_Change()
        }

    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        C.TitleBackBtn.backpress = true
//    }


    private fun setTexts() {
        my_info_name_et.setText(ProfileData.user_name)
        my_info_phone_et.setText(ProfileData.phone_number)
        my_info_email_et.setText(ProfileData.user_email)
        my_info_country_select_tv.text = C.NationalCode.valueOf(ProfileData.country_cd).country_nm

    }

    private fun Pwd_Change() {
        C.TitleBackBtn.closeOR = false
        val intent = Intent(this, PwdChangeActivity::class.java)
        Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
    }

//    private fun InfoChange() {
//        val intent = Intent(this, InfoChangeActivity::class.java)
//        startActivity(intent)
//    }
//
//    private fun ChangeInfo() {
//        val intent = Intent(this, InfoChangeActivity::class.java)
//        startActivity(intent)
//    }

}