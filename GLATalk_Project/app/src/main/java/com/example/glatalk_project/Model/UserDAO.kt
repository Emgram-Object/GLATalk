package com.example.glatalk_project.Model

import android.util.Log
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.LangRequest
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.request.PwdFindRequest
import com.example.glatalk_project.network.data.request.UserRequest
import com.example.glatalk_project.network.data.response.LoginResponse
import com.example.glatalk_project.network.data.response.ProfileResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface UserIntDAO{
    fun login(loginRequest: LoginRequest):Call<LoginResponse>
    fun findPwd(findRequest: PwdFindRequest):Call<BaseResponse>
    fun add(userRequest: UserRequest):Call<BaseResponse>
    fun userLanguage(request: LangRequest):Call<BaseResponse>
    fun userDetail():Call<ProfileResponse>
}


object UserDAO {
    var apiServer:UserIntDAO
    init {
       apiServer =  ApiServer.retrofit.create(UserIntDAO::class.java)
    }

    fun login(loginRequest: LoginRequest, callback: Callback<LoginResponse>){
        apiServer.login(loginRequest).enqueue(callback)
        apiServer.login(loginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                TODO("Not yet implemented")
            }

        })
    }

//    override fun findPwd(findRequest: PwdFindRequest): Single<BaseResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override fun add(userRequest: UserRequest): Single<BaseResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override fun userLanguage(request: LangRequest): Single<BaseResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override fun userDetail(): Single<ProfileResponse> {
//        TODO("Not yet implemented")
//    }
}
