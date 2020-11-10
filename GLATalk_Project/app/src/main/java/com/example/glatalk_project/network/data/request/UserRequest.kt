package com.example.glatalk_project.network.data.request

data class UserRequest (
        val user_name:String,
        val user_email:String,
        val user_type:String,
        val user_pwd:String,
        val mobile_num:String,
        val country_cd:String,
        val guide_info:String,
        val guide_time:String,
        val ad_agree:Boolean
)//VO (userRequest를 위한)

