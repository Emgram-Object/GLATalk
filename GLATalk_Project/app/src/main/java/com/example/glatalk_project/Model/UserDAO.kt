package com.example.glatalk_project.Model

import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.request.PwdFindRequest
import com.example.glatalk_project.network.data.request.UserRequest
import com.example.glatalk_project.util.SharedPreferenceUtil
import retrofit2.Callback

object UserDAO {
    var userVO = UserVO()

    fun login(loginRequest: LoginRequest, callback: Callback<BaseResponse>) {
        ApiServer.network.login(loginRequest).enqueue(callback)
    }

    fun findPwd(pwdFindRequest: PwdFindRequest, callback: Callback<BaseResponse>) {
        ApiServer.network.pwd_find(pwdFindRequest).enqueue(callback)
    }
    fun isAuto():Boolean = SharedPreferenceUtil.getBoolean(C.Preference.KEY_IS_AUTO_LOGIN,false)

    fun add( userRequest: UserRequest, callback: Callback<BaseResponse>) {
        ApiServer.network.add(userRequest).enqueue(callback)
    }
    fun logout(callback: Callback<BaseResponse>) {
        SharedPreferenceUtil.putBoolean(C.Preference.KEY_IS_AUTO_LOGIN,false)
        ApiServer.network.logout().enqueue(callback)

    }

    //
//    override fun userLanguage(request: LangRequest): Single<BaseResponse> {
//    }
//

//    fun setUserId(user_email: String) {
//        PreferenceUtil.putString(C.Preference.KEY_USER_ID, user_email)
//    }
//    fun setAccessToken(token: String){
//
//    }
}

