package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.galtalk_project.Controller.InfoChangeActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.ProfileData
import com.example.glatalk_project.R
import com.example.glatalk_project.TokenData
import com.example.glatalk_project.network.BaseResponse
import com.google.gson.JsonElement
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.activity_my_info.*
import kotlinx.android.synthetic.main.ui_common_title.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MyInfoActivity : AppCompatActivity() {

    var myDao = MyDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        InfoNetworking()

        setTexts()




        change_tv.visibility = View.VISIBLE

        change_tv.setOnClickListener() {
            InfoChange()
        }

        my_info_pwd_change_btn.setOnClickListener {
            Pwd_Change()
        }

    }

    private fun InfoNetworking(){
        myDao.detail_info(callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("qwert", "onFailure: fail")
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("qwert", "onResponse: success")
                var result = response.body()!!
                var resultCode = result.resultCode
                var desc = result.desc
                var body = result.body.toString()

                var jsonObject:JSONObject = JSONObject(body);
                ProfileData.user_name = jsonObject["user_name"].toString()
                ProfileData.phone_number = jsonObject["phone_number"].toString()
                ProfileData.country_cd = jsonObject["country_cd"].toString()
                ProfileData.user_email= jsonObject["user_email"].toString()
                ProfileData.user_type = jsonObject["user_type"].toString()


                Log.d("result", "$body")
                Log.d("result", "${ProfileData.user_name}")
                Log.d("result", "${ProfileData.phone_number}")
                Log.d("result", "${ProfileData.country_cd}")
                Log.d("result", "${ProfileData.user_email}")
                Log.d("result", "${ProfileData.user_type}")


            }
//            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
//                Log.d("qwert", "onFailure: fail")
//            }
//            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {

////
//            }
        })
    }
    private fun setTexts(){
        my_info_name_et.setText(ProfileData.user_name)
        my_info_phone_et.setText(ProfileData.phone_number)
        my_info_email_et.setText(ProfileData.user_email)
        my_info_country_select_tv.text = ProfileData.country_cd
    }
    private fun Pwd_Change() {
        val intent = Intent(this, PwdChangeActivity::class.java)
        startActivity(intent)
    }

    private fun InfoChange() {
        val intent = Intent(this, InfoChangeActivity::class.java)
        startActivity(intent)
    }


    private fun ChangeInfo() {
        val intent = Intent(this, InfoChangeActivity::class.java)
        startActivity(intent)
    }

}