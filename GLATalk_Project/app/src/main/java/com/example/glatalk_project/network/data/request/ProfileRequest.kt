package com.example.glatalk_project.network.data.request

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class ProfileRequest(
        val profile_img: MultipartBody.Part?,
        val user:User
)
data class User(
        val user_name:RequestBody,
        val phone_number:RequestBody?,
        val country_cd:RequestBody
)