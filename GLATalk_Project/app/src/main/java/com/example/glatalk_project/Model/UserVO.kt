package com.example.glatalk_project.Model

data class UserVO(var user_email : String = "", var user_pwd: String = "", var pwd_check: String ="", var user_type: String = "", var user_name : String ="",
                  var phone_number : String = "", var country_cd: String = "", var guide_info: String = "", var guide_time: String = "",var ad_agree:Boolean=true){
    init{
        this.user_email = user_email
        this.user_pwd = user_pwd
        this.pwd_check = pwd_check
        this.user_type = user_type
        this.user_name = user_name
        this.phone_number = phone_number
        this.country_cd = country_cd
        this.guide_info = guide_info
        this.guide_time = guide_time
        this.ad_agree = ad_agree


    }

    override fun toString(): String {
        return "UserVO(user_email='$user_email', user_pwd='$user_pwd', pwd_check='$pwd_check', user_type='$user_type', user_name='$user_name', mobile_num='$phone_number', country_cd='$country_cd', guide_info='$guide_info', guide_time='$guide_time',ad_agree='$ad_agree')"
    }
}

//    //data 클래스로 바꿔주기 (Model은 데이터 클래스)
//    //DAO 따로 만들어주기(기본 클래스 만들고 데이터 뷰에서 가져오기 )
//    // 네트워크 클래스는 통합적으로 하나 만들어주공...
//    // 네트워크 - 데이터 클래스 api 분류 따라서 리케 리스폰 만들어서 거기서 API 관리
