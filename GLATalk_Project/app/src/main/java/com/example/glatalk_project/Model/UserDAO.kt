package com.example.glatalk_project.Model

import com.example.glatalk_project.TokenData
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.request.PwdFindRequest
import com.example.glatalk_project.network.data.request.UserRequest
import retrofit2.Callback

object UserDAO {
    var userVO = UserVO()

    fun login(loginRequest: LoginRequest, callback: Callback<BaseResponse>) {
        ApiServer.network.login(loginRequest).enqueue(callback)
    }

    fun findPwd(pwdFindRequest: PwdFindRequest, callback: Callback<BaseResponse>) {
        ApiServer.network.pwd_find(pwdFindRequest).enqueue(callback)
    }

    fun add( userRequest: UserRequest, callback: Callback<BaseResponse>) {

        ApiServer.network.add(userRequest).enqueue(callback)

    }

    //
//    override fun userLanguage(request: LangRequest): Single<BaseResponse> {
//        TODO("Not yet implemented")
//    }
//
    fun userDetail(callback: Callback<BaseResponse>) {
        ApiServer.network.detail_info(TokenData.loginToken).enqueue(callback)

    }
//    fun setUserId(user_email: String) {
//        PreferenceUtil.putString(C.Preference.KEY_USER_ID, user_email)
//    }
//    fun setAccessToken(token: String){
//
//    }
}

