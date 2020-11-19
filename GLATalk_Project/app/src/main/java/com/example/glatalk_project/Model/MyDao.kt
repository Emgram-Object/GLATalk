package com.example.glatalk_project.Model

import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.ProfileRequest
import com.example.glatalk_project.network.data.request.PwdRequest
import retrofit2.Callback

object MyDao {

    fun change_pwd(pwdRequest: PwdRequest, callback: Callback<BaseResponse>) {
        ApiServer.network.change_pwd(pwdRequest).enqueue(callback)
    }

    fun detail_info(callback: Callback<BaseResponse>) {
        ApiServer.network.detail_info().enqueue(callback)
    }

    fun modify_info(profileRequest: ProfileRequest, callback: Callback<BaseResponse>) {
        ApiServer.network.modify_info(profileRequest.user_name, profileRequest.phone_number, profileRequest.country_cd).enqueue(callback)
    }
}

