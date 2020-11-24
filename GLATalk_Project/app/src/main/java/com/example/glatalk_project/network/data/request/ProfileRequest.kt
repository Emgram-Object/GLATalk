package com.example.glatalk_project.network.data.request

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class ProfileRequest(
//        val profile_img:MultipartBody.Part?,
        val user_name: String,
        val phone_number: String,
        val country_cd: String?,
)
