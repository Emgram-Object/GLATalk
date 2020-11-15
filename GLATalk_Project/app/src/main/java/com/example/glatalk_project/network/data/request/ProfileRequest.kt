package com.example.glatalk_project.network.data.request

import com.example.glatalk_project.TokenData
import okhttp3.MultipartBody
import okhttp3.RequestBody
//
//class ProfileRequest {
//
//    var Authorization = TokenData.loginToken
//}

data class ProfileRequest(
        val profile_img: MultipartBody.Part?,
        val user: User
)

data class User(
        val user_name: RequestBody,
        val user_phone_num: RequestBody?,
        val country_cd: RequestBody,
        val user_EngName: RequestBody?
)