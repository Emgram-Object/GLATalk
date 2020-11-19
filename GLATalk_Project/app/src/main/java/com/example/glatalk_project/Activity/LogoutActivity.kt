package com.example.glatalk_project.Activity

import android.content.Intent

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.TokenData
import com.example.glatalk_project.UserData
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.BaseResponse
import retrofit2.Callback
import com.example.glatalk_project.util.PreferenceUtil
import retrofit2.Call
import retrofit2.Response


 object LogoutActivity{
    private var userDAO = UserDAO
    var userData = UserData()


    fun logout() {
//        PreferenceUtil.putString(C.Preference.KEY_ACCESS_TOKEN, "")
//        PreferenceUtil.putBoolean(C.Preference.KEY_IS_AUTO_LOGIN, false)
        logoutNetworking()
    }
     fun logoutNetworking() {
        userDAO.logout(callback = object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                userData.resultCode = result.resultCode.toString()
                userData.desc = result.desc.toString()
            }

        })
    }
}
