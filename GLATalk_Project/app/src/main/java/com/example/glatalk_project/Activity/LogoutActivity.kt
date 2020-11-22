package com.example.glatalk_project.Activity

import android.util.Log
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.Data.UserData
import com.example.glatalk_project.network.data.response.BaseResponse
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


 object LogoutActivity{
    private var userDAO = UserDAO
    var userData = UserData()


    fun logout() {

//        PreferenceUtil.putString(C.Preference.KEY_ACCESS_TOKEN, "")
//        PreferenceUtil.putBoolean(C.Preference.KEY_IS_AUTO_LOGIN, false)
        doLogout()
    }

     fun doLogout() {
        userDAO.logout(callback = object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                userData.resultCode = result.resultCode.toString()
                userData.desc = result.desc.toString()
                if (response.isSuccessful) {
                    Log.d("logout", TokenData.loginToken.toString())

                    Log.d("after logout", TokenData.loginToken.toString())
                }
            }

        })
    }
}
