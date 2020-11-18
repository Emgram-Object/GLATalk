package com.example.glatalk_project

data class ProfileData(
        var user_name: String = "",
        var phone_number: String = "",
        var country_cd: String = "",
        var user_email: String = "",
        var user_type: String = "", )
{
    init {
        this.user_name = user_name
        this.phone_number = phone_number
        this.country_cd = country_cd
        this.user_email = user_email
        this.user_type = user_type
    }

}