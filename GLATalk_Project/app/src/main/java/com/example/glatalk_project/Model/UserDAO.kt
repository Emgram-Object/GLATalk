package com.example.glatalk_project.Model

import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.request.UserRequest
import com.example.glatalk_project.network.networkInterface
import retrofit2.Callback

interface UserIntDAO {
//    fun login(loginRequest: LoginRequest): Call<BaseResponse>
//    fun findPwd(findRequest: PwdFindRequest): Call<BaseResponse>
//    fun add(userRequest: UserRequest): Call<BaseResponse>
//    fun userLanguage(request: LangRequest): Call<BaseResponse>
//    fun userDetail(): Call<ProfileResponse>
}


object UserDAO {
    var userVO = UserVO()
    var apiServer: networkInterface

    init {
        apiServer = ApiServer.retrofit.create(networkInterface::class.java)
    }

    fun login(loginRequest: LoginRequest, callback: Callback<BaseResponse>) {
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
//    fun setAccessToken(token: String){
//
//    }
}

