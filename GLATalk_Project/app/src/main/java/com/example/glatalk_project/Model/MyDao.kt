package com.example.glatalk_project.Model

import android.util.Log
import com.example.glatalk_project.Activity.LoginActivity
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.data.request.ProfileRequest
import com.example.glatalk_project.network.data.request.PwdRequest
import com.example.glatalk_project.network.data.response.BaseResponse
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MyDao {

    fun change_pwd(pwdRequest: PwdRequest, callback: Callback<BaseResponse>) {
        ApiServer.network.change_pwd(pwdRequest).enqueue(callback)
    }

    fun detail_info(callback: Callback<BaseResponse>) {
        ApiServer.network.detail_info().enqueue(callback)
    }

    fun modify_info(profileRequest: ProfileRequest, callback: Callback<BaseResponse>) {
//        ApiServer.network.modify_info(profileRequest.user_name, profileRequest.phone_number, profileRequest.country_cd).enqueue(callback)
        ApiServer.network.modify_info(profileRequest).enqueue(callback)
    }

    fun getInfo(inter: LoginActivity?){
        Log.d("tokenData", "${TokenData.loginToken}")

        detail_info(callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                var resultCode = result.resultCode
                var desc = result.desc
                var body = result.body.toString()
                val regex = "[^=,{}\\[\\] ]{1,}=[^=,{}\\[\\] ]{0,}".toRegex()
                try {
                    body = regex.replace(body) {
                        var text = it.value.substring(it.value.indexOf('=') + 1)
                        if (text.equals("null")) {
                            "\"" + it.value.substring(0, it.value.indexOf('=')) + "\":null"
                        } else {
                            "\"" + it.value.substring(0, it.value.indexOf('=')) + "\":\"$text\""
                        }
                    }
                    var jsonObject: JSONObject = JSONObject(body)
                    ProfileData.user_name = jsonObject["user_name"].toString()
                    ProfileData.phone_number = jsonObject["phone_number"].toString()
                    ProfileData.country_cd = jsonObject["country_cd"].toString()
                    ProfileData.user_email = jsonObject["user_email"].toString()
                    ProfileData.user_type = jsonObject["user_type"].toString()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }
}

