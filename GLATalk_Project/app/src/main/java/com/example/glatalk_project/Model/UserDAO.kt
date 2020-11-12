package com.example.glatalk_project.Model

import android.util.Log
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.LangRequest
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.request.PwdFindRequest
import com.example.glatalk_project.network.data.request.UserRequest
import com.example.glatalk_project.network.data.response.LoginResponse
import com.example.glatalk_project.network.data.response.ProfileResponse
import com.example.glatalk_project.network.networkInterface
import com.example.glatalk_project.util.PreferenceUtil
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface UserIntDAO {
    fun login(loginRequest: LoginRequest): Call<LoginResponse>
    fun findPwd(findRequest: PwdFindRequest): Call<BaseResponse>
    fun add(userRequest: UserRequest): Call<BaseResponse>
    fun userLanguage(request: LangRequest): Call<BaseResponse>
    fun userDetail(): Call<ProfileResponse>
}


object UserDAO {
    var userVO = UserVO()
    var apiServer: networkInterface

    init {
        apiServer = ApiServer.retrofit.create(networkInterface::class.java)
    }

    fun login(loginRequest: LoginRequest, callback: Callback<LoginResponse>) {
        apiServer.login(loginRequest).enqueue(callback)
    }

    //    override fun findPwd(findRequest: PwdFindRequest): Single<BaseResponse> {
//        TODO("Not yet implemented")
//    }
//
    fun add(userRequest: UserRequest, callback: Callback<BaseResponse>) {
        apiServer.add(userRequest).enqueue(callback)
    }

    //
//    override fun userLanguage(request: LangRequest): Single<BaseResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override fun userDetail(): Single<ProfileResponse> {
//        TODO("Not yet implemented")
//    }
//    fun setUserId(user_email: String) {
//        PreferenceUtil.putString(C.Preference.KEY_USER_ID, user_email)
//    }
}
