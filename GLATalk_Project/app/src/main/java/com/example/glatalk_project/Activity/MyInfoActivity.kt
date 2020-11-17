package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.galtalk_project.Controller.InfoChangeActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.TokenData
import com.example.glatalk_project.network.BaseResponse
import kotlinx.android.synthetic.main.activity_my_info.*
import kotlinx.android.synthetic.main.ui_common_title.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        var myDao = MyDao


        myDao.detail_info(callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
             Log.d("qwert", "onFailure: fail")
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
             Log.d("qwert", "onResponse: success")
                var result = response.body()!!
                var resultCode = result.resultCode
                var desc = result.desc
                var bodys = result.body.toString()
                Log.d("qwert", "$bodys")
                Log.d("TAG", "onResponse: ")

            }
//            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
//                Log.d("qwert", "onFailure: fail")
//            }
//            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {

////
//            }
        })

        change_tv.visibility = View.VISIBLE

        change_tv.setOnClickListener() {
            InfoChange()
        }

        my_info_pwd_change_btn.setOnClickListener {
            Pwd_Change()
        }

    }


    private fun Pwd_Change() {
        val intent = Intent(this, PwdChangeActivity::class.java)
        startActivity(intent)
    }

    private fun InfoChange() {
        val intent = Intent(this, InfoChangeActivity::class.java)
        startActivity(intent)
    }

//
//        my_info_name_et.setText(userVo.user_name)
//        my_info_phone_et.setText(userVo.phone_number)
//        my_info_email_et.setText(userVo.user_email)
//        my_info_country_select_tv.setText(userVo.country_cd)


    private fun ChangeInfo() {
        val intent = Intent(this, InfoChangeActivity::class.java)
        startActivity(intent)
    }

}