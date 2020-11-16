package com.example.glatalk_project.Model

import com.example.glatalk_project.TokenData
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.PwdRequest
import com.example.glatalk_project.network.networkInterface
import retrofit2.Callback

object MyDao{

    fun change_pwd(pwdRequest: PwdRequest, callback: Callback<BaseResponse>){
        ApiServer.network.change_pwd(pwdRequest).enqueue(callback)
    }
}
