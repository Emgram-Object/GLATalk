package com.example.glatalk_project.Model

import com.example.glatalk_project.TokenData
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.request.ProfileRequest
import com.example.glatalk_project.network.data.request.PwdFindRequest
import com.example.glatalk_project.network.data.request.UserRequest
import com.example.glatalk_project.network.data.response.ProfileResponse
import com.example.glatalk_project.network.networkInterface
import retrofit2.Callback

object UserDAO {
    var userVO = UserVO()
    var apiServer: networkInterface

    init {
        apiServer = ApiServer.retrofit.create(networkInterface::class.java)
    }

    fun login(loginRequest: LoginRequest, callback: Callback<BaseResponse>) {
        apiServer.login(loginRequest).enqueue(callback)
    }

    fun findPwd(pwdFindRequest: PwdFindRequest, callback: Callback<BaseResponse>) {
        apiServer.pwd_find(pwdFindRequest).enqueue(callback)
    }

    fun add( userRequest: UserRequest, callback: Callback<BaseResponse>) {
        apiServer.add(userRequest).enqueue(callback)
    }

    //
//    override fun userLanguage(request: LangRequest): Single<BaseResponse> {
//        TODO("Not yet implemented")
//    }
//
    fun userDetail(callback: Callback<BaseResponse>) {
       apiServer.detail_info(TokenData.loginToken).enqueue(callback)
    }
//    fun setUserId(user_email: String) {
//        PreferenceUtil.putString(C.Preference.KEY_USER_ID, user_email)
//    }
//    fun setAccessToken(token: String){
//
//    }
}

