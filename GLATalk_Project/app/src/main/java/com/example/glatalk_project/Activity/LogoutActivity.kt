package com.example.glatalk_project.Activity

import android.util.Log
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.Data.UserData
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.util.SharedPreferenceUtil
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

//로그아웃 시 토큰 초기화하기

 object LogoutActivity{
    private var userDAO = UserDAO
    var userData = UserData()


     fun doLogout() {
        userDAO.logout(callback = object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                userData.resultCode = result.resultCode.toString()
                userData.desc = result.desc.toString()
                if (response.isSuccessful) {
                    TokenData.loginToken = ""
                    userDAO.clearLoginToken()
                    userDAO.setAutoLogin(false)
                    Log.d("logout_TokenData", "${TokenData.loginToken}")
                    Log.d("logout_token", "${{SharedPreferenceUtil.getString(C.Preference.KEY_ACCESS_TOKEN)}}")

                }
            }
        })
    }
}
