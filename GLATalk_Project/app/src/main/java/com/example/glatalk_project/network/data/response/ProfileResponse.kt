package com.example.glatalk_project.network.data.response

import com.example.glatalk_project.network.BaseResponse

class ProfileResponse (
    var profile_url: String = "",
    var user_name: String = "",
    var phone_number: String = "",
    var country_cd: String = "",
    var user_email: String = ""
):BaseResponse(){

}
