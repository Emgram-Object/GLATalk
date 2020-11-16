package com.example.glatalk_project.Activity

import android.content.Intent
import android.net.DnsResolver
import android.os.Bundle
import android.os.PersistableBundle
import android.telecom.Call
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.TokenData
import com.example.glatalk_project.UserData
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.error.AppError
import com.example.glatalk_project.util.PreferenceUtil
import okhttp3.Response
import javax.security.auth.callback.Callback

class LogoutActivity:AppCompatActivity(){
    private var userDAO = UserDAO
    var userData = UserData()
    var tokenData = TokenData
    var userDao = UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("login", "${TokenData.loginToken}")

//        logoutNetworking()
        logout()
        Log.d("login", "${TokenData.loginToken}")


    }


    fun logout() {
        PreferenceUtil.putString(C.Preference.KEY_ACCESS_TOKEN, "")
        PreferenceUtil.putBoolean(C.Preference.KEY_IS_AUTO_LOGIN, false)
        tokenData.loginToken = ""
       // goLogin()

    }

    private fun goLogin(){
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}
