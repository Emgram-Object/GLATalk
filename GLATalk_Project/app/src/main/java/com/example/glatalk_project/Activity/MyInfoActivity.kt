package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.R
import com.example.glatalk_project.network.data.response.BaseResponse
import kotlinx.android.synthetic.main.activity_my_info.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoActivity : AppCompatActivity() {

    var myDao = MyDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        common_title_my_info.setTitle("내 정보")
        common_title_my_info.setModifyBtn()

        myDao.getInfo(null)

        setTexts()

        my_info_pwd_change_btn.setOnClickListener {
            Pwd_Change()
        }
    }


    private fun setTexts() {
        my_info_name_et.setText(ProfileData.user_name)
        my_info_phone_et.setText(ProfileData.phone_number)
        my_info_email_et.setText(ProfileData.user_email)
        //      my_info_country_select_tv.text = ProfileData.country_cd

    }

    private fun Pwd_Change() {
        val intent = Intent(this, PwdChangeActivity::class.java)
        startActivity(intent)
    }

//    private fun InfoChange() {
//        val intent = Intent(this, InfoChangeActivity::class.java)
//        startActivity(intent)
//    }
}