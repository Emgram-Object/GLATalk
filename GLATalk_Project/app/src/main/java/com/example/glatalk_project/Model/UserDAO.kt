package com.example.glatalk_project.Model

import android.util.Log
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.MainApiServer
import com.example.glatalk_project.network.data.request.LangRequest
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.request.PwdFindRequest
import com.example.glatalk_project.network.data.request.UserRequest
import com.example.glatalk_project.network.data.response.LoginResponse
import com.example.glatalk_project.network.data.response.ProfileResponse
import com.example.glatalk_project.network.error.AppError
import com.example.glatalk_project.util.PreferenceUtil
import io.reactivex.Single
import retrofit2.Call

interface UserIntDAO{
    fun login(loginRequest: LoginRequest):Single<LoginResponse>
    fun findPwd(findRequest: PwdFindRequest):Single<BaseResponse>
    fun add(userRequest: UserRequest):Single<BaseResponse>
    fun userLanguage(request: LangRequest):Single<BaseResponse>
    fun userDetail():Single<ProfileResponse>
}


class UserDAO:UserIntDAO {
    override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return MainApiServer.API.login(loginRequest).
    }
}
//    var accessToken: String? =""
//    var user_id:String?=""
//    var country_cd:String =""
//    var language_code:String =""
//    set(lang) {
//        PreferenceUtil.putStringSync(C.Preference.SELECTED_LANGUAGE,lang)
//        field = lang
//        Log.d("UserDAO","lang: "+lang)
//    }
//    init {
//        load()
//    }
//    private fun load(){
//        language_code = PreferenceUtil.getString(C.Preference.SELECTED_LANGUAGE)
//    }
//
//    companion object{
//        private var _instance:UserDAO?=null
//        val instance:UserDAO
//        get() {
//            if (_instance==null){
//                _instance = UserDAO()
//            }
//            return _instance!!
//        }
//    }
