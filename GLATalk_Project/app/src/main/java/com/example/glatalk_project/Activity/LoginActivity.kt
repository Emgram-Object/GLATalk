package com.example.glatalk_project.Activity

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.response.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserDAO.login(loginRequest = LoginRequest("","") , callback = object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("d", "onFailure: 실패 수고링방구링 ")
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("ㅇ", "onResponse: 성공  ")
            }
        })


    }

}