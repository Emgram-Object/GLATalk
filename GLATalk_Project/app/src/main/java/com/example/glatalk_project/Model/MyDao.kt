package com.example.glatalk_project.Model

import com.example.glatalk_project.TokenData
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.PwdRequest
import com.example.glatalk_project.network.networkInterface
import retrofit2.Callback

object MyDao{
    var apiServer : networkInterface

    init {
        apiServer = ApiServer.retrofit.create(networkInterface::class.java)
    }
    fun change_pwd(pwdRequest1: String, pwdRequest: PwdRequest, callback: Callback<BaseResponse>){
        apiServer.change_pwd(TokenData.loginToken,pwdRequest).enqueue(callback)
    }
}